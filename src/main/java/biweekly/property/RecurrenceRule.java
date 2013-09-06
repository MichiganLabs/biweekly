package biweekly.property;

import java.util.List;

import biweekly.component.ICalComponent;
import biweekly.util.Recurrence;

/*
 Copyright (c) 2013, Michael Angstadt
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * <p>
 * Defines how often a component repeats.
 * </p>
 * <p>
 * <b>Examples:</b>
 * 
 * <pre>
 * //&quot;bi-weekly&quot;
 * Recurrence recur = new Recurrence.Builder(Frequency.WEEKLY).interval(2).build();
 * RecurrenceRule rrule = new RecurrenceRule(recur);
 * </pre>
 * 
 * </p>
 * @author Michael Angstadt
 * @see <a href="http://tools.ietf.org/html/rfc5545#page-122">RFC 5545
 * p.122-32</a>
 */
public class RecurrenceRule extends RecurrenceProperty {
	/**
	 * Creates a new recurrence rule property.
	 * @param recur the recurrence rule
	 */
	public RecurrenceRule(Recurrence recur) {
		super(recur);
	}

	@Override
	protected void validate(List<ICalComponent> components, List<String> warnings) {
		super.validate(components, warnings);
		if (value == null) {
			return;
		}

		if (!value.getXRules().isEmpty()) {
			warnings.add("Non-standard rule parts are not allowed in the latest iCal specification.");
		}
	}
}
