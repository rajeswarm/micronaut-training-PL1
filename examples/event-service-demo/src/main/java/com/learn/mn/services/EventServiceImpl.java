package com.learn.mn.services;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.CalendarEvent;

import io.micronaut.core.async.publisher.Publishers;
import jakarta.inject.Singleton;

@Singleton
class EventServiceImpl implements EventService {
	
	private Map<String, CalendarEvent> eventsMap = new HashMap<>();
	
	@PostConstruct
	void initEventsMap() {
		eventsMap.put("event-1", new CalendarEvent("event-1", "first event", Duration.ofHours(1)));
		eventsMap.put("event-2", new CalendarEvent("event-2", "second event", Duration.ofHours(2)));
		eventsMap.put("event-3", new CalendarEvent("event-3", "third event", Duration.ofHours(3)));
	}

	@Override
	public Publisher<CalendarEvent> getEventbyId(String eventId) {
	    return Publishers.just(eventsMap.get(eventId));
	}

}
