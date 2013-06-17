package biweekly.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
 * Manages objects that are like enums in that they are constant, but unlike
 * enums in that new instances can be created during runtime. This class ensures
 * that all instances of a class are unique, so they can be safely compared
 * using "==" (provided their constructors are private). It mimics the
 * "case class" feature in Scala.
 * @author Michael Angstadt
 * 
 * @param <T> the class
 * @param <V> the value that the class holds (e.g. String)
 */
public abstract class CaseClasses<T, V> {
	protected final Class<T> clazz;
	protected Collection<T> preDefined = null;
	protected Collection<T> runtimeDefined = null;

	/**
	 * Creates a new case class collection.
	 * @param clazz the case class
	 */
	public CaseClasses(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Creates a new instance of the case class.
	 * @param value the value to give the instance
	 * @return the new instance
	 */
	protected abstract T create(V value);

	/**
	 * Gets the value of a case object.
	 * @param object the object
	 * @return the object's value
	 */
	protected abstract V valueOf(T object);

	/**
	 * Determines if a value is associated with a case object.
	 * @param object the object
	 * @param value the value
	 * @return true if it matches, false if not
	 */
	protected abstract boolean matches(T object, V value);

	/**
	 * Searches for a case object by value, only looking at the case class'
	 * static constants (does not include runtime-defined objects).
	 * @param value the value
	 * @return the object or null if one wasn't found
	 */
	public T find(V value) {
		return find(value, false, false);
	}

	/**
	 * Searches for a case object by value, creating a new object if one cannot
	 * be found.
	 * @param value the value
	 * @return the object
	 */
	public T get(V value) {
		return find(value, true, true);
	}

	/**
	 * Searches for a case object by value.
	 * @param value the value
	 * @param createIfNotFound true to create a new instance of the object if it
	 * can't be found, false to return "null" if not found
	 * @param searchRuntimeDefined true to include the runtime-defined objects
	 * in the search, false not to
	 * @return the object
	 */
	protected T find(V value, boolean createIfNotFound, boolean searchRuntimeDefined) {
		if (preDefined == null) {
			init();
		}

		for (T obj : preDefined) {
			if (matches(obj, value)) {
				return obj;
			}
		}

		if (searchRuntimeDefined) {
			for (T obj : runtimeDefined) {
				if (matches(obj, value)) {
					return obj;
				}
			}
			if (createIfNotFound) {
				T created = create(value);
				runtimeDefined.add(created);
				return created;
			}
		}
		return null;
	}

	/**
	 * Gets all the static constants of the case class.
	 * @return all static constants
	 */
	public Collection<T> all() {
		if (preDefined == null) {
			init();
		}
		return preDefined;
	}

	@SuppressWarnings("unchecked")
	private void init() {
		preDefined = new ArrayList<T>();
		runtimeDefined = new ArrayList<T>(0);

		for (Field field : clazz.getFields()) {
			int modifiers = field.getModifiers();
			//@formatter:off
			if (Modifier.isStatic(modifiers) &&
				Modifier.isPublic(modifiers) &&
				field.getDeclaringClass() == clazz &&
				field.getType() == clazz) {
				//@formatter:on
				try {
					Object obj = field.get(null);
					if (obj != null) {
						T c = (T) obj;
						preDefined.add(c);
					}
				} catch (Exception ex) {
					//reflection error
					//should never be throw because we check for "public static" and the correct type
				}
			}
		}

		preDefined = Collections.unmodifiableCollection(preDefined);
	}
}