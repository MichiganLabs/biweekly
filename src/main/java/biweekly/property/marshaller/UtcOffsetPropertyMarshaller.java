package biweekly.property.marshaller;

import java.util.List;

import biweekly.parameter.ICalParameters;
import biweekly.property.UtcOffsetProperty;
import biweekly.util.ICalDateFormatter;


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
 * Marshals properties that have UTC offset values.
 * @author Michael Angstadt
 */
public abstract class UtcOffsetPropertyMarshaller<T extends UtcOffsetProperty> extends ICalPropertyMarshaller<T> {
	public UtcOffsetPropertyMarshaller(Class<T> clazz, String propertyName) {
		super(clazz, propertyName);
	}

	@Override
	protected String _writeText(T property) {
		Integer hour = property.getHourOffset();
		if (hour == null) {
			hour = 0;
		}

		Integer minute = property.getMinuteOffset();
		if (minute == null) {
			minute = 0;
		}

		return ICalDateFormatter.formatTimeZone(hour, minute, false);
	}

	@Override
	protected T _parseText(String value, ICalParameters parameters, List<String> warnings) {
		value = unescape(value);

		Integer hourOffset = null, minuteOffset = null;
		try {
			int[] offset = ICalDateFormatter.parseTimeZone(value);
			hourOffset = offset[0];
			minuteOffset = offset[1];
		} catch (IllegalArgumentException e) {
			warnings.add("Could not parse offset string: " + value);
		}
		return newInstance(hourOffset, minuteOffset);
	}

	protected abstract T newInstance(Integer hourOffset, Integer minuteOffset);
}