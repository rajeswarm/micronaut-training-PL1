package com.learn.mn.pojo;

import java.time.Duration;

public class CalendarEvent {
	
	private String eventId;
	private String eventTitle;
    private Duration eventDuration;
    
	public CalendarEvent() {
		super();
	}
	public CalendarEvent(String eventId, String eventTitle, Duration eventDuration) {
		super();
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventDuration = eventDuration;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public Duration getEventDuration() {
		return eventDuration;
	}
	public void setEventDuration(Duration eventDuration) {
		this.eventDuration = eventDuration;
	}

}
