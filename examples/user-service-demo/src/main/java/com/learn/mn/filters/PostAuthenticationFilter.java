package com.learn.mn.filters;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.http.filter.ServerFilterPhase;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;

@Filter(patterns = Filter.MATCH_ALL_PATTERN)
public class PostAuthenticationFilter implements HttpServerFilter {

	private final Logger logger = LoggerFactory.getLogger(PostAuthenticationFilter.class);

	@Inject
	SecurityService securityService;

	@Override
	public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {

		if (securityService.isAuthenticated()) {
			Authentication authentication = securityService.getAuthentication().get();
			logger.info("{} accessed by {} with roles {}", request.getPath(), authentication.getName(),
					authentication.getRoles());
		} else {
			logger.info("{} accessed by anonymous user", request.getPath());
		}
		return chain.proceed(request);
	}
	
	@Override
	public int getOrder() {
		return ServerFilterPhase.LAST.order();
	}

}
