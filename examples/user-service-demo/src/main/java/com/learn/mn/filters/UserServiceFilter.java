package com.learn.mn.filters;

import org.reactivestreams.Publisher;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;

@Filter(patterns = Filter.MATCH_ALL_PATTERN)
public class UserServiceFilter implements HttpServerFilter {

	@Override
	public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
		String correlationId = request.getHeaders().get("X-CORRELATION-ID");
		System.out.println("Received request at " + request.getPath() + " with correlation id: " + correlationId);
		return chain.proceed(request);
	}

}
