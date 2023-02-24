package com.learn.mn.controllers;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.CalendarEvent;
import com.learn.mn.services.EventService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.inject.Inject;

@Controller("/events")
public class EventController {

	@Inject
	EventService eventService;

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{eventId}")
	public Publisher<CalendarEvent> getEventById(@PathVariable("eventId") String eventId) {
		return eventService.getEventbyId(eventId);
	}

}
