package com.learn.mn.filters;

import java.util.UUID;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;

@Filter(patterns = Filter.MATCH_ALL_PATTERN)
public class UserServiceFilter implements HttpServerFilter {

	Logger logger = LoggerFactory.getLogger(UserServiceFilter.class);

	@Override
	public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
		String correlationId = request.getHeaders().get("X-CORRELATION-ID");
		if (correlationId == null) {
			correlationId = UUID.randomUUID().toString();
		}
		logger.info("Received request at {} with correlation id: {}", request.getPath(), correlationId);
		return chain.proceed(request);
	}

}
