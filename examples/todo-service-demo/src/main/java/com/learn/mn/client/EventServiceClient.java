package com.learn.mn.client;

import javax.validation.constraints.NotBlank;

import com.learn.mn.pojo.CalendarEvent;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Client(value = "event-service-demo")
@CircuitBreaker(attempts = "3", delay = "1s")
public interface EventServiceClient {

	@Get(uri = "/events/{eventId}")
	Mono<CalendarEvent> getEventById(@NotBlank @PathVariable("eventId") String eventId, @Header("X-CORRELATION-ID") String correlationId);
}
