// Copyright (C) 2006 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package biweekly.util.com.google.ical.iter;

import biweekly.util.com.google.ical.values.DateValue;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * A recurrence iterator that combines multiple recurrence iterators into one.
 * @author mikesamuel+svn@gmail.com (Mike Samuel)
 */
final class CompoundIteratorImpl implements RecurrenceIterator {
  /**
   * A queue that keeps the earliest dates at the head.
   */
  private final PriorityQueue<HeapElement> queue;
  
  private HeapElement pending;
  
  /**
   * The number of inclusions on the queue. We keep track of this so that we
   * don't have to drain the exclusions to conclude that the series is
   * exhausted.
   */
  private int nInclusionsRemaining;

  /**
   * Creates an iterator that will generate only dates that are generated by
   * inclusions and will not generate any dates that are generated by exclusions
   * (i.e. exclusions trump inclusions).
   * @param inclusions iterators whose elements should be included unless
   * explicitly excluded
   * @param exclusions iterators whose elements should not be included
   */
  CompoundIteratorImpl(
      Collection<RecurrenceIterator> inclusions,
      Collection<RecurrenceIterator> exclusions) {
    queue = new PriorityQueue<HeapElement>(
        inclusions.size() + exclusions.size(), HeapElement.CMP);
    for (RecurrenceIterator it : inclusions) {
      HeapElement el = new HeapElement(true, it);
      if (el.shift()) {
        queue.add(el);
        ++nInclusionsRemaining;
      }
    }
    for (RecurrenceIterator it : exclusions) {
      HeapElement el = new HeapElement(false, it);
      if (el.shift()) { queue.add(el); }
    }
  }

  public boolean hasNext() {
    requirePending();
    return pending != null;
  }

  public DateValue next() {
    requirePending();
    if (pending == null) { throw new NoSuchElementException(); }
    DateValue head = pending.head();
    reattach(pending);
    pending = null;
    return head;
  }

  public void remove() { throw new UnsupportedOperationException(); }

  public void advanceTo(DateValue newStart) {
    long newStartCmp = DateValueComparison.comparable(newStart);
    if (pending != null) {
      if (pending.comparable() >= newStartCmp) { return; }
      pending.advanceTo(newStart);
      reattach(pending);
      pending = null;
    }

    /*
     * Pull each element off the stack in turn, and advance it. Once we reach
     * one we don't need to advance, we're done.
     */
    while (nInclusionsRemaining != 0 && !queue.isEmpty()
           && queue.peek().comparable() < newStartCmp) {
      HeapElement el = queue.poll();
      el.advanceTo(newStart);
      reattach(el);
    }
  }

  /**
   * If the given element's iterator has more data, then push back onto the
   * heap.
   * @param el the element to push back into the heap.
   */
  private void reattach(HeapElement el) {
    if (el.shift()) {
      queue.add(el);
    } else if (el.inclusion) {
      /*
       * If we have no live inclusions, then the rest are exclusions which we
       * can safely discard.
       */
      if (--nInclusionsRemaining == 0) {
        queue.clear();
      }
    }
  }

  /**
   * Makes sure that pending contains the next inclusive {@link HeapElement}
   * that doesn't match any exclusion, and remove any duplicates of it.
   */
  private void requirePending() {
    if (pending != null) { return; }

    long exclusionComparable = Long.MIN_VALUE;
    while (nInclusionsRemaining != 0 && !queue.isEmpty()) {
      //find a candidate that is not excluded
      HeapElement inclusion = null;
      do {
        HeapElement candidate = queue.poll();
        if (candidate.inclusion) {
          if (exclusionComparable != candidate.comparable()) {
            inclusion = candidate;
            break;
          }
        } else {
          exclusionComparable = candidate.comparable();
        }
        reattach(candidate);
        if (nInclusionsRemaining == 0) { return; }
      } while (!queue.isEmpty());
      if (inclusion == null) { return; }
      long inclusionComparable = inclusion.comparable();

      /*
       * Check for any following exclusions and for duplicates. We could change
       * the sort order so that exclusions always preceded inclusions, but that
       * would be less efficient and would make the ordering different than the
       * comparable value.
       */
      boolean excluded = exclusionComparable == inclusionComparable;
      while (!queue.isEmpty()
             && queue.peek().comparable() == inclusionComparable) {
        HeapElement match = queue.poll();
        excluded |= !match.inclusion;
        reattach(match);
        if (nInclusionsRemaining == 0) { return; }
      }
      if (!excluded) {
        pending = inclusion;
        return;
      }
      reattach(inclusion);
    }
  }
}

final class HeapElement {
  /**
   * Should iterators items be included in the series or should they
   * nullify any matched items included by other series?
   */
  final boolean inclusion;
  
  private final RecurrenceIterator it;
  
  /**
   * The {@link DateValueComparison#comparable} for {@link #head}.
   */
  private long comparable;
  
  /**
   * The last value removed from the iterator (in UTC).
   */
  private DateValue head;

  HeapElement(boolean inclusion, RecurrenceIterator it) {
    this.inclusion = inclusion;
    this.it = it;
  }

  /**
   * Gets the last value removed from the iterator.
   */
  DateValue head() { return head; }
  
  /**
   * Gets the comparable value of the head. A given HeapElement may be compared
   * to many others as it bubbles towards the heap's root, so we cache this for
   * each HeapElement.
   * @return the comparable value of the head
   */
  long comparable() { return comparable; }
  
  /**
   * Discards the current element and move to the next.
   * @return true if there is a next element, false if not
   */
  boolean shift() {
    if (!it.hasNext()) { return false; }
    head = it.next();
    comparable = DateValueComparison.comparable(head);
    return true;
  }

  /**
   * Advances the underlying iterator to the given date value.
   * @param newStartUtc the date to advance to (in UTC)
   * @see RecurrenceIterator#advanceTo
   */
  void advanceTo(DateValue newStartUtc) {
    it.advanceTo(newStartUtc);
  }

  @Override
  public String toString() {
    return
      "[" + head.toString() + ", " + (inclusion ? "inclusion" : "exclusion") + "]";
  }

  /**
   * Compares two heap elements by comparing their heads.
   */
  static final Comparator<HeapElement> CMP = new Comparator<HeapElement>() {
    public int compare(HeapElement a, HeapElement b) {
      long ac = a.comparable(),
           bc = b.comparable();
      return ac < bc ? -1 : ac == bc ? 0 : 1;
    }
  };
}
