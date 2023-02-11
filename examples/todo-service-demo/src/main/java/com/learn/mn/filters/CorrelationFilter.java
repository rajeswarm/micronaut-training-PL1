package com.learn.mn.filters;

import java.util.UUID;

import org.reactivestreams.Publisher;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import reactor.core.publisher.Flux;

@Filter(Filter.MATCH_ALL_PATTERN)
public class CorrelationFilter implements HttpServerFilter{

	@Override
	public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
		String correlationId = UUID.randomUUID().toString();
		request.getAttributes().put("correlationId", correlationId);
		
		return Flux.from(chain.proceed(request)).doOnNext( r-> r.getHeaders().add("X-CORRELATION-ID", correlationId));
	}

}
