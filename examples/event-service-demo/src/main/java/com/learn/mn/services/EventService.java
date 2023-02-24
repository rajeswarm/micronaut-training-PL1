package com.learn.mn.services;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.CalendarEvent;

public interface EventService {

	Publisher<CalendarEvent> getEventbyId(String eventId);
}
