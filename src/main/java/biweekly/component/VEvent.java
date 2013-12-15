package biweekly.component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import biweekly.Warning;
import biweekly.property.Attachment;
import biweekly.property.Attendee;
import biweekly.property.Categories;
import biweekly.property.Classification;
import biweekly.property.Comment;
import biweekly.property.Contact;
import biweekly.property.Created;
import biweekly.property.DateEnd;
import biweekly.property.DateStart;
import biweekly.property.DateTimeStamp;
import biweekly.property.Description;
import biweekly.property.DurationProperty;
import biweekly.property.ExceptionDates;
import biweekly.property.ExceptionRule;
import biweekly.property.Geo;
import biweekly.property.LastModified;
import biweekly.property.Location;
import biweekly.property.Method;
import biweekly.property.Organizer;
import biweekly.property.Priority;
import biweekly.property.RecurrenceDates;
import biweekly.property.RecurrenceId;
import biweekly.property.RecurrenceRule;
import biweekly.property.RelatedTo;
import biweekly.property.RequestStatus;
import biweekly.property.Resources;
import biweekly.property.Sequence;
import biweekly.property.Status;
import biweekly.property.Summary;
import biweekly.property.Transparency;
import biweekly.property.Uid;
import biweekly.property.Url;
import biweekly.util.Duration;
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
 * Defines a scheduled activity, such as a meeting that's two hours long.
 * </p>
 * <p>
 * <b>Examples:</b>
 * 
 * <pre class="brush:java">
 * VEvent event = new VEvent();
 * Date start = ...
 * event.setDateStart(start);
 * Date end = ...
 * event.setDateEnd(end);
 * event.setSummary("Team Meeting");
 * event.setLocation("Room 21C");
 * event.setCreated(new Date());
 * event.setRecurrenceRule(new Recurrence.Builder(Frequency.WEEKLY).build());
 * </pre>
 * 
 * </p>
 * @author Michael Angstadt
 * @rfc 5545 p.52-5
 */
public class VEvent extends ICalComponent {
	/**
	 * <p>
	 * Creates a new event.
	 * </p>
	 * <p>
	 * The following properties are auto-generated on object creation. These
	 * properties <b>must</b> be present in order for the event to be valid:
	 * <ul>
	 * <li>{@link Uid} - Set to a UUID.</li>
	 * <li>{@link DateTimeStamp} - Set to the current date-time.</li>
	 * </ul>
	 * </p>
	 */
	public VEvent() {
		setUid(Uid.random());
		setDateTimeStamp(new Date());
	}

	/**
	 * Gets the unique identifier for this event. This component object comes
	 * populated with a UID on creation. This is a <b>required</b> property.
	 * @return the UID or null if not set
	 * @rfc 5545 p.117-8
	 */
	public Uid getUid() {
		return getProperty(Uid.class);
	}

	/**
	 * Sets the unique identifier for this event. This component object comes
	 * populated with a UID on creation. This is a <b>required</b> property.
	 * @param uid the UID or null to remove
	 * @rfc 5545 p.117-8
	 */
	public void setUid(Uid uid) {
		setProperty(Uid.class, uid);
	}

	/**
	 * Sets the unique identifier for this event. This component object comes
	 * populated with a UID on creation. This is a <b>required</b> property.
	 * @param uid the UID or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.117-8
	 */
	public Uid setUid(String uid) {
		Uid prop = (uid == null) ? null : new Uid(uid);
		setUid(prop);
		return prop;
	}

	/**
	 * Gets either (a) the creation date of the iCalendar object (if the
	 * {@link Method} property is defined) or (b) the date that the event was
	 * last modified (the {@link LastModified} property also holds this
	 * information). This event object comes populated with a
	 * {@link DateTimeStamp} property that is set to the current time. This is a
	 * <b>required</b> property.
	 * @return the date time stamp or null if not set
	 * @rfc 5545 p.137-8
	 */
	public DateTimeStamp getDateTimeStamp() {
		return getProperty(DateTimeStamp.class);
	}

	/**
	 * Sets either (a) the creation date of the iCalendar object (if the
	 * {@link Method} property is defined) or (b) the date that the event was
	 * last modified (the {@link LastModified} property also holds this
	 * information). This event object comes populated with a
	 * {@link DateTimeStamp} property that is set to the current time. This is a
	 * <b>required</b> property.
	 * @param dateTimeStamp the date time stamp or null to remove
	 * @rfc 5545 p.137-8
	 */
	public void setDateTimeStamp(DateTimeStamp dateTimeStamp) {
		setProperty(DateTimeStamp.class, dateTimeStamp);
	}

	/**
	 * Sets either (a) the creation date of the iCalendar object (if the
	 * {@link Method} property is defined) or (b) the date that the event was
	 * last modified (the {@link LastModified} property also holds this
	 * information). This event object comes populated with a
	 * {@link DateTimeStamp} property that is set to the current time. This is a
	 * <b>required</b> property.
	 * @param dateTimeStamp the date time stamp or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.137-8
	 */
	public DateTimeStamp setDateTimeStamp(Date dateTimeStamp) {
		DateTimeStamp prop = (dateTimeStamp == null) ? null : new DateTimeStamp(dateTimeStamp);
		setDateTimeStamp(prop);
		return prop;
	}

	/**
	 * Gets the date that the event starts.
	 * @return the start date or null if not set
	 * @rfc 5545 p.97-8
	 */
	public DateStart getDateStart() {
		return getProperty(DateStart.class);
	}

	/**
	 * Sets the date that the event starts (required if no {@link Method}
	 * property is defined).
	 * @param dateStart the start date or null to remove
	 * @rfc 5545 p.97-8
	 */
	public void setDateStart(DateStart dateStart) {
		setProperty(DateStart.class, dateStart);
	}

	/**
	 * Sets the date that the event starts (required if no {@link Method}
	 * property is defined).
	 * @param dateStart the start date or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.97-8
	 */
	public DateStart setDateStart(Date dateStart) {
		DateStart prop = (dateStart == null) ? null : new DateStart(dateStart);
		setDateStart(prop);
		return prop;
	}

	/**
	 * Gets the level of sensitivity of the event data. If not specified, the
	 * data within the event should be considered "public".
	 * @return the classification level or null if not set
	 * @rfc 5545 p.82-3
	 */
	public Classification getClassification() {
		return getProperty(Classification.class);
	}

	/**
	 * Sets the level of sensitivity of the event data. If not specified, the
	 * data within the event should be considered "public".
	 * @param classification the classification level or null to remove
	 * @rfc 5545 p.82-3
	 */
	public void setClassification(Classification classification) {
		setProperty(Classification.class, classification);
	}

	/**
	 * Sets the level of sensitivity of the event data. If not specified, the
	 * data within the event should be considered "public".
	 * @param classification the classification level (e.g. "CONFIDENTIAL") or
	 * null to remove
	 * @return the property that was created
	 * @rfc 5545 p.82-3
	 */
	public Classification setClassification(String classification) {
		Classification prop = (classification == null) ? null : new Classification(classification);
		setClassification(prop);
		return prop;
	}

	/**
	 * Gets a detailed description of the event. The description should be more
	 * detailed than the one provided by the {@link Summary} property.
	 * @return the description or null if not set
	 * @rfc 5545 p.84-5
	 */
	public Description getDescription() {
		return getProperty(Description.class);
	}

	/**
	 * Sets a detailed description of the event. The description should be more
	 * detailed than the one provided by the {@link Summary} property.
	 * @param description the description or null to remove
	 * @rfc 5545 p.84-5
	 */
	public void setDescription(Description description) {
		setProperty(Description.class, description);
	}

	/**
	 * Sets a detailed description of the event. The description should be more
	 * detailed than the one provided by the {@link Summary} property.
	 * @param description the description or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.84-5
	 */
	public Description setDescription(String description) {
		Description prop = (description == null) ? null : new Description(description);
		setDescription(prop);
		return prop;
	}

	/**
	 * Gets a set of geographical coordinates.
	 * @return the geographical coordinates or null if not set
	 * @rfc 5545 p.85-7
	 */
	public Geo getGeo() {
		return getProperty(Geo.class);
	}

	/**
	 * Sets a set of geographical coordinates.
	 * @param geo the geographical coordinates or null to remove
	 * @rfc 5545 p.85-7
	 */
	public void setGeo(Geo geo) {
		setProperty(Geo.class, geo);
	}

	/**
	 * Gets the physical location of the event.
	 * @return the location or null if not set
	 * @rfc 5545 p.87-8
	 */
	public Location getLocation() {
		return getProperty(Location.class);
	}

	/**
	 * Sets the physical location of the event.
	 * @param location the location or null to remove
	 * @rfc 5545 p.87-8
	 */
	public void setLocation(Location location) {
		setProperty(Location.class, location);
	}

	/**
	 * Sets the physical location of the event.
	 * @param location the location (e.g. "Room 101") or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.87-8
	 */
	public Location setLocation(String location) {
		Location prop = (location == null) ? null : new Location(location);
		setLocation(prop);
		return prop;
	}

	/**
	 * Gets the priority of the event.
	 * @return the priority or null if not set
	 * @rfc 5545 p.89-90
	 */
	public Priority getPriority() {
		return getProperty(Priority.class);
	}

	/**
	 * Sets the priority of the event.
	 * @param priority the priority or null to remove
	 * @rfc 5545 p.89-90
	 */
	public void setPriority(Priority priority) {
		setProperty(Priority.class, priority);
	}

	/**
	 * Sets the priority of the event.
	 * @param priority the priority ("0" is undefined, "1" is the highest, "9"
	 * is the lowest) or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.89-90
	 */
	public Priority setPriority(Integer priority) {
		Priority prop = (priority == null) ? null : new Priority(priority);
		setPriority(prop);
		return prop;
	}

	/**
	 * Gets the status of the event.
	 * @return the status or null if not set
	 * @rfc 5545 p.92-3
	 */
	public Status getStatus() {
		return getProperty(Status.class);
	}

	/**
	 * Sets the status of the event.
	 * <p>
	 * Valid event status codes are:
	 * <ul>
	 * <li>TENTATIVE</li>
	 * <li>CONFIRMED</li>
	 * <li>CANCELLED</li>
	 * </ul>
	 * </p>
	 * @param status the status or null to remove
	 * @rfc 5545 p.92-3
	 */
	public void setStatus(Status status) {
		setProperty(Status.class, status);
	}

	/**
	 * Gets the summary of the event.
	 * @return the summary or null if not set
	 * @rfc 5545 p.93-4
	 */
	public Summary getSummary() {
		return getProperty(Summary.class);
	}

	/**
	 * Sets the summary of the event.
	 * @param summary the summary or null to remove
	 * @rfc 5545 p.93-4
	 */
	public void setSummary(Summary summary) {
		setProperty(Summary.class, summary);
	}

	/**
	 * Sets the summary of the event.
	 * @param summary the summary or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.93-4
	 */
	public Summary setSummary(String summary) {
		Summary prop = (summary == null) ? null : new Summary(summary);
		setSummary(prop);
		return prop;
	}

	/**
	 * Gets whether an event is visible to free/busy time searches. If the event
	 * does not have this property, it should be considered visible ("opaque").
	 * @return the transparency or null if not set
	 * @rfc 5545 p.101-2
	 */
	public Transparency getTransparency() {
		return getProperty(Transparency.class);
	}

	/**
	 * Sets whether an event is visible to free/busy time searches.
	 * @param transparency the transparency or null to remove
	 * @rfc 5545 p.101-2
	 */
	public void setTransparency(Transparency transparency) {
		setProperty(Transparency.class, transparency);
	}

	/**
	 * Sets whether an event is visible to free/busy time searches.
	 * @param transparent true to hide the event, false to make it visible it,
	 * or null to remove the property
	 * @return the property that was created
	 * @rfc 5545 p.101-2
	 */
	public Transparency setTransparency(Boolean transparent) {
		Transparency prop = null;
		if (transparent != null) {
			prop = transparent ? Transparency.transparent() : Transparency.opaque();
		}
		setTransparency(prop);
		return prop;
	}

	/**
	 * Gets the organizer of the event.
	 * @return the organizer or null if not set
	 * @rfc 5545 p.111-2
	 */
	public Organizer getOrganizer() {
		return getProperty(Organizer.class);
	}

	/**
	 * Sets the organizer of the event.
	 * @param organizer the organizer or null to remove
	 * @rfc 5545 p.111-2
	 */
	public void setOrganizer(Organizer organizer) {
		setProperty(Organizer.class, organizer);
	}

	/**
	 * Sets the organizer of the event.
	 * @param email the organizer's email address (e.g. "johndoe@example.com")
	 * or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.111-2
	 */
	public Organizer setOrganizer(String email) {
		Organizer prop = (email == null) ? null : Organizer.email(email);
		setOrganizer(prop);
		return prop;
	}

	/**
	 * Gets the original value of the {@link DateStart} property if the event is
	 * recurring and has been modified. Used in conjunction with the {@link Uid}
	 * and {@link Sequence} properties to uniquely identify a recurrence
	 * instance.
	 * @return the recurrence ID or null if not set
	 * @rfc 5545 p.112-4
	 */
	public RecurrenceId getRecurrenceId() {
		return getProperty(RecurrenceId.class);
	}

	/**
	 * Sets the original value of the {@link DateStart} property if the event is
	 * recurring and has been modified. Used in conjunction with the {@link Uid}
	 * and {@link Sequence} properties to uniquely identify a recurrence
	 * instance.
	 * @param recurrenceId the recurrence ID or null to remove
	 * @rfc 5545 p.112-4
	 */
	public void setRecurrenceId(RecurrenceId recurrenceId) {
		setProperty(RecurrenceId.class, recurrenceId);
	}

	/**
	 * Sets the original value of the {@link DateStart} property if the event is
	 * recurring and has been modified. Used in conjunction with the {@link Uid}
	 * and {@link Sequence} properties to uniquely identify a recurrence
	 * instance.
	 * @param originalStartDate the original start date or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.112-4
	 */
	public RecurrenceId setRecurrenceId(Date originalStartDate) {
		RecurrenceId prop = (originalStartDate == null) ? null : new RecurrenceId(originalStartDate);
		setRecurrenceId(prop);
		return prop;
	}

	/**
	 * Gets a URL to a resource that contains additional information about the
	 * event.
	 * @return the URL or null if not set
	 * @rfc 5545 p.116-7
	 */
	public Url getUrl() {
		return getProperty(Url.class);
	}

	/**
	 * Sets a URL to a resource that contains additional information about the
	 * event.
	 * @param url the URL or null to remove
	 * @rfc 5545 p.116-7
	 */
	public void setUrl(Url url) {
		setProperty(Url.class, url);
	}

	/**
	 * Sets a URL to a resource that contains additional information about the
	 * event.
	 * @param url the URL (e.g. "http://example.com/resource.ics") or null to
	 * remove
	 * @return the property that was created
	 * @rfc 5545 p.116-7
	 */
	public Url setUrl(String url) {
		Url prop = (url == null) ? null : new Url(url);
		setUrl(prop);
		return prop;
	}

	/**
	 * Gets how often the event repeats.
	 * @return the recurrence rule or null if not set
	 * @rfc 5545 p.122-32
	 */
	public RecurrenceRule getRecurrenceRule() {
		return getProperty(RecurrenceRule.class);
	}

	/**
	 * Sets how often the event repeats.
	 * @param recur the recurrence rule or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.122-32
	 */
	public RecurrenceRule setRecurrenceRule(Recurrence recur) {
		RecurrenceRule prop = (recur == null) ? null : new RecurrenceRule(recur);
		setRecurrenceRule(prop);
		return prop;
	}

	/**
	 * Sets how often the event repeats.
	 * @param recurrenceRule the recurrence rule or null to remove
	 * @rfc 5545 p.122-32
	 */
	public void setRecurrenceRule(RecurrenceRule recurrenceRule) {
		setProperty(RecurrenceRule.class, recurrenceRule);
	}

	/**
	 * Gets the date that the event ends.
	 * @return the end date or null if not set
	 * @rfc 5545 p.95-6
	 */
	public DateEnd getDateEnd() {
		return getProperty(DateEnd.class);
	}

	/**
	 * Sets the date that the event ends. This must NOT be set if a
	 * {@link DurationProperty} is defined.
	 * @param dateEnd the end date or null to remove
	 * @rfc 5545 p.95-6
	 */
	public void setDateEnd(DateEnd dateEnd) {
		setProperty(DateEnd.class, dateEnd);
	}

	/**
	 * Sets the date that the event ends. This must NOT be set if a
	 * {@link DurationProperty} is defined.
	 * @param dateEnd the end date or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.95-6
	 */
	public DateEnd setDateEnd(Date dateEnd) {
		DateEnd prop = (dateEnd == null) ? null : new DateEnd(dateEnd);
		setDateEnd(prop);
		return prop;
	}

	/**
	 * Gets the duration of the event.
	 * @return the duration or null if not set
	 * @rfc 5545 p.99
	 */
	public DurationProperty getDuration() {
		return getProperty(DurationProperty.class);
	}

	/**
	 * Sets the duration of the event. This must NOT be set if a {@link DateEnd}
	 * is defined.
	 * @param duration the duration or null to remove
	 * @rfc 5545 p.99
	 */
	public void setDuration(DurationProperty duration) {
		setProperty(DurationProperty.class, duration);
	}

	/**
	 * Sets the duration of the event. This must NOT be set if a {@link DateEnd}
	 * is defined.
	 * @param duration the duration or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.99
	 */
	public DurationProperty setDuration(Duration duration) {
		DurationProperty prop = (duration == null) ? null : new DurationProperty(duration);
		setDuration(prop);
		return prop;
	}

	/**
	 * Gets the date-time that the event was initially created.
	 * @return the creation date-time or null if not set
	 * @rfc 5545 p.136
	 */
	public Created getCreated() {
		return getProperty(Created.class);
	}

	/**
	 * Sets the date-time that the event was initially created.
	 * @param created the creation date-time or null to remove
	 * @rfc 5545 p.136
	 */
	public void setCreated(Created created) {
		setProperty(Created.class, created);
	}

	/**
	 * Sets the date-time that the event was initially created.
	 * @param created the creation date-time or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.136
	 */
	public Created setCreated(Date created) {
		Created prop = (created == null) ? null : new Created(created);
		setCreated(prop);
		return prop;
	}

	/**
	 * Gets the date-time that the event was last changed.
	 * @return the last modified date or null if not set
	 * @rfc 5545 p.138
	 */
	public LastModified getLastModified() {
		return getProperty(LastModified.class);
	}

	/**
	 * Sets the date-time that event was last changed.
	 * @param lastModified the last modified date or null to remove
	 * @rfc 5545 p.138
	 */
	public void setLastModified(LastModified lastModified) {
		setProperty(LastModified.class, lastModified);
	}

	/**
	 * Sets the date-time that the event was last changed.
	 * @param lastModified the last modified date or null to remove
	 * @return the property that was created
	 * @rfc 5545 p.138
	 */
	public LastModified setLastModified(Date lastModified) {
		LastModified prop = (lastModified == null) ? null : new LastModified(lastModified);
		setLastModified(prop);
		return prop;
	}

	/**
	 * Gets the revision number of the event. The organizer can increment this
	 * number every time he or she makes a significant change.
	 * @return the sequence number
	 * @rfc 5545 p.138-9
	 */
	public Sequence getSequence() {
		return getProperty(Sequence.class);
	}

	/**
	 * Sets the revision number of the event. The organizer can increment this
	 * number every time he or she makes a significant change.
	 * @param sequence the sequence number
	 * @rfc 5545 p.138-9
	 */
	public void setSequence(Sequence sequence) {
		setProperty(Sequence.class, sequence);
	}

	/**
	 * Sets the revision number of the event. The organizer can increment this
	 * number every time he or she makes a significant change.
	 * @param sequence the sequence number
	 * @return the property that was created
	 * @rfc 5545 p.138-9
	 */
	public Sequence setSequence(Integer sequence) {
		Sequence prop = (sequence == null) ? null : new Sequence(sequence);
		setSequence(prop);
		return prop;
	}

	/**
	 * Increments the revision number of the event. The organizer can increment
	 * this number every time he or she makes a significant change.
	 * @rfc 5545 p.138-9
	 */
	public void incrementSequence() {
		Sequence sequence = getSequence();
		if (sequence == null) {
			setSequence(1);
		} else {
			sequence.increment();
		}
	}

	/**
	 * Gets any attachments that are associated with the event.
	 * @return the attachments
	 * @rfc 5545 p.80-1
	 */
	public List<Attachment> getAttachments() {
		return getProperties(Attachment.class);
	}

	/**
	 * Adds an attachment to the event.
	 * @param attachment the attachment to add
	 * @rfc 5545 p.80-1
	 */
	public void addAttachment(Attachment attachment) {
		addProperty(attachment);
	}

	/**
	 * Gets the people who are attending the event.
	 * @return the attendees
	 * @rfc 5545 p.107-9
	 */
	public List<Attendee> getAttendees() {
		return getProperties(Attendee.class);
	}

	/**
	 * Adds a person who is attending the event.
	 * @param attendee the attendee
	 * @rfc 5545 p.107-9
	 */
	public void addAttendee(Attendee attendee) {
		addProperty(attendee);
	}

	/**
	 * Adds a person who is attending the event.
	 * @param email the attendee's email address
	 * @return the property that was created
	 * @rfc 5545 p.107-9
	 */
	public Attendee addAttendee(String email) {
		Attendee prop = Attendee.email(email);
		addAttendee(prop);
		return prop;
	}

	/**
	 * Gets a list of "tags" or "keywords" that describe the event.
	 * @return the categories
	 * @rfc 5545 p.81-2
	 */
	public List<Categories> getCategories() {
		return getProperties(Categories.class);
	}

	/**
	 * Adds a list of "tags" or "keywords" that describe the event. Note that a
	 * single property can hold multiple keywords.
	 * @param categories the categories to add
	 * @rfc 5545 p.81-2
	 */
	public void addCategories(Categories categories) {
		addProperty(categories);
	}

	/**
	 * Adds a list of "tags" or "keywords" that describe the event.
	 * @param categories the categories to add
	 * @return the property that was created
	 * @rfc 5545 p.81-2
	 */
	public Categories addCategories(String... categories) {
		Categories prop = new Categories(categories);
		addCategories(prop);
		return prop;
	}

	/**
	 * Adds a list of "tags" or "keywords" that describe the event.
	 * @param categories the categories to add
	 * @return the property that was created
	 * @rfc 5545 p.81-2
	 */
	public Categories addCategories(List<String> categories) {
		Categories prop = new Categories(categories);
		addCategories(prop);
		return prop;
	}

	/**
	 * Gets the comments attached to the event.
	 * @return the comments
	 * @rfc 5545 p.83-4
	 */
	public List<Comment> getComments() {
		return getProperties(Comment.class);
	}

	/**
	 * Adds a comment to the event.
	 * @param comment the comment to add
	 * @rfc 5545 p.83-4
	 */
	public void addComment(Comment comment) {
		addProperty(comment);
	}

	/**
	 * Adds a comment to the event.
	 * @param comment the comment to add
	 * @return the property that was created
	 * @rfc 5545 p.83-4
	 */
	public Comment addComment(String comment) {
		Comment prop = new Comment(comment);
		addComment(prop);
		return prop;
	}

	/**
	 * Gets the contacts associated with the event.
	 * @return the contacts
	 * @rfc 5545 p.109-11
	 */
	public List<Contact> getContacts() {
		return getProperties(Contact.class);
	}

	/**
	 * Adds a contact to the event.
	 * @param contact the contact
	 * @rfc 5545 p.109-11
	 */
	public void addContact(Contact contact) {
		addProperty(contact);
	}

	/**
	 * Adds a contact to the event.
	 * @param contact the contact (e.g. "ACME Co - (123) 555-1234")
	 * @return the property that was created
	 * @rfc 5545 p.109-11
	 */
	public Contact addContact(String contact) {
		Contact prop = new Contact(contact);
		addContact(prop);
		return prop;
	}

	/**
	 * Gets the list of exceptions to the recurrence rule defined in the event
	 * (if one is defined).
	 * @return the list of exceptions
	 * @rfc 5545 p.118-20
	 */
	public List<ExceptionDates> getExceptionDates() {
		return getProperties(ExceptionDates.class);
	}

	/**
	 * Adds a list of exceptions to the recurrence rule defined in the event (if
	 * one is defined). Note that this property can contain multiple dates.
	 * @param exceptionDates the list of exceptions
	 * @rfc 5545 p.118-20
	 */
	public void addExceptionDates(ExceptionDates exceptionDates) {
		addProperty(exceptionDates);
	}

	/**
	 * Gets the response to a scheduling request.
	 * @return the response
	 * @rfc 5545 p.141-3
	 */
	public RequestStatus getRequestStatus() {
		return getProperty(RequestStatus.class);
	}

	/**
	 * Sets the response to a scheduling request.
	 * @param requestStatus the response
	 * @rfc 5545 p.141-3
	 */
	public void setRequestStatus(RequestStatus requestStatus) {
		setProperty(RequestStatus.class, requestStatus);
	}

	/**
	 * Gets the components that the event is related to.
	 * @return the relationships
	 * @rfc 5545 p.115-6
	 */
	public List<RelatedTo> getRelatedTo() {
		return getProperties(RelatedTo.class);
	}

	/**
	 * Adds a component that the event is related to.
	 * @param relatedTo the relationship
	 * @rfc 5545 p.115-6
	 */
	public void addRelatedTo(RelatedTo relatedTo) {
		//TODO create a method that accepts a component and make the RelatedTo property invisible to the user
		//@formatter:off
		/*
		 * addRelation(RelationshipType relType, ICalComponent component){
		 *   RelatedTo prop = new RelatedTo(component.getUid().getValue());
		 *   prop.setRelationshipType(relType);
		 *   addProperty(prop);
		 * }
		 */
		//@formatter:on
		addProperty(relatedTo);
	}

	/**
	 * Adds a component that the event is related to.
	 * @param uid the UID of the other component
	 * @return the property that was created
	 * @rfc 5545 p.115-6
	 */
	public RelatedTo addRelatedTo(String uid) {
		RelatedTo prop = new RelatedTo(uid);
		addRelatedTo(prop);
		return prop;
	}

	/**
	 * Gets the resources that are needed for the event.
	 * @return the resources
	 * @rfc 5545 p.91
	 */
	public List<Resources> getResources() {
		return getProperties(Resources.class);
	}

	/**
	 * Adds a list of resources that are needed for the event. Note that a
	 * single property can hold multiple resources.
	 * @param resources the resources to add
	 * @rfc 5545 p.91
	 */
	public void addResources(Resources resources) {
		addProperty(resources);
	}

	/**
	 * Adds a list of resources that are needed for the event.
	 * @param resources the resources to add (e.g. "easel", "projector")
	 * @return the property that was created
	 * @rfc 5545 p.91
	 */
	public Resources addResources(String... resources) {
		Resources prop = new Resources(resources);
		addResources(prop);
		return prop;
	}

	/**
	 * Adds a list of resources that are needed for the event.
	 * @param resources the resources to add (e.g. "easel", "projector")
	 * @return the property that was created
	 * @rfc 5545 p.91
	 */
	public Resources addResources(List<String> resources) {
		Resources prop = new Resources(resources);
		addResources(prop);
		return prop;
	}

	/**
	 * Gets the list of dates/periods that help define the recurrence rule of
	 * this event (if one is defined).
	 * @return the recurrence dates
	 * @rfc 5545 p.120-2
	 */
	public List<RecurrenceDates> getRecurrenceDates() {
		return getProperties(RecurrenceDates.class);
	}

	/**
	 * Adds a list of dates/periods that help define the recurrence rule of this
	 * event (if one is defined).
	 * @param recurrenceDates the recurrence dates
	 * @rfc 5545 p.120-2
	 */
	public void addRecurrenceDates(RecurrenceDates recurrenceDates) {
		addProperty(recurrenceDates);
	}

	/**
	 * Gets the alarms that are assigned to this event.
	 * @return the alarms
	 * @rfc 5545 p.71-6
	 */
	public List<VAlarm> getAlarms() {
		return getComponents(VAlarm.class);
	}

	/**
	 * Adds an alarm to this event.
	 * @param alarm the alarm
	 * @rfc 5545 p.71-6
	 */
	public void addAlarm(VAlarm alarm) {
		addComponent(alarm);
	}

	/**
	 * <p>
	 * Gets the exceptions for the {@link RecurrenceRule} property.
	 * </p>
	 * <p>
	 * Note that this property has been removed from the latest version of the
	 * iCal specification. Its use should be avoided.
	 * </p>
	 * @return the exception rules
	 * @rfc 2445 p.114-15
	 */
	public List<ExceptionRule> getExceptionRules() {
		return getProperties(ExceptionRule.class);
	}

	/**
	 * <p>
	 * Adds an exception for the {@link RecurrenceRule} property.
	 * </p>
	 * <p>
	 * Note that this property has been removed from the latest version of the
	 * iCal specification. Its use should be avoided.
	 * </p>
	 * @param recur the exception rule to add
	 * @return the property that was created
	 * @rfc 2445 p.114-15
	 */
	public ExceptionRule addExceptionRule(Recurrence recur) {
		ExceptionRule prop = (recur == null) ? null : new ExceptionRule(recur);
		addExceptionRule(prop);
		return prop;
	}

	/**
	 * <p>
	 * Adds an exception for the {@link RecurrenceRule} property.
	 * </p>
	 * <p>
	 * Note that this property has been removed from the latest version of the
	 * iCal specification. Its use should be avoided.
	 * </p>
	 * @param exceptionRule the exception rule to add
	 * @rfc 2445 p.114-15
	 */
	public void addExceptionRule(ExceptionRule exceptionRule) {
		addProperty(exceptionRule);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void validate(List<ICalComponent> components, List<Warning> warnings) {
		checkRequiredCardinality(warnings, Uid.class, DateTimeStamp.class);
		checkOptionalCardinality(warnings, Classification.class, Created.class, Description.class, Geo.class, LastModified.class, Location.class, Organizer.class, Priority.class, Priority.class, Status.class, Summary.class, Transparency.class, Url.class, RecurrenceId.class);

		Status status = getStatus();
		if (status != null && (status.isNeedsAction() || status.isCompleted() || status.isInProgress() || status.isDraft() || status.isFinal())) {
			warnings.add(Warning.validate(13, status.getValue(), Arrays.asList(Status.tentative().getValue(), Status.confirmed().getValue(), Status.cancelled().getValue())));
		}

		DateStart dateStart = getDateStart();
		DateEnd dateEnd = getDateEnd();

		ICalComponent ical = components.get(0);
		if (dateStart == null && ical.getProperty(Method.class) == null) {
			warnings.add(Warning.validate(14));
		}

		if (dateEnd != null && dateStart == null) {
			warnings.add(Warning.validate(15));
		}

		if (dateStart != null && dateEnd != null) {
			Date start = dateStart.getValue();
			Date end = dateEnd.getValue();
			if (start != null && end != null && start.compareTo(end) > 0) {
				warnings.add(Warning.validate(16));
			}

			if (dateStart.hasTime() != dateEnd.hasTime()) {
				warnings.add(Warning.validate(17));
			}
		}

		if (dateEnd != null && getDuration() != null) {
			warnings.add(Warning.validate(18));
		}

		RecurrenceId recurrenceId = getRecurrenceId();
		if (recurrenceId != null && dateStart != null && dateStart.hasTime() != recurrenceId.hasTime()) {
			warnings.add(Warning.validate(19));
		}

		//RFC 5545 p. 167
		RecurrenceRule rrule = getRecurrenceRule();
		if (dateStart != null && rrule != null) {
			Date start = dateStart.getValue();
			Recurrence recur = rrule.getValue();
			if (start != null && recur != null) {
				if (!dateStart.hasTime() && (!recur.getByHour().isEmpty() || !recur.getByMinute().isEmpty() || !recur.getBySecond().isEmpty())) {
					warnings.add(Warning.validate(5));
				}
			}
		}

		//RFC 5545 p. 167
		if (getProperties(RecurrenceRule.class).size() > 1) {
			warnings.add(Warning.validate(6));
		}

		//TODO check for properties which shouldn't be added to VEVENTs
	}
}
