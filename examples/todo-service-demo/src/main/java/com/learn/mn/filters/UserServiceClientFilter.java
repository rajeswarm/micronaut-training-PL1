package com.learn.mn.filters;

import java.util.Date;

import org.reactivestreams.Publisher;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;

@Filter("/user-info/**")
public class UserServiceClientFilter implements HttpClientFilter {

	@Override
	public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
		System.out.println("Starting Http Client Call to: " + request.getPath());
		
		Long startMilliSec = new Date().getTime();

		return Publishers.map(chain.proceed(request), r -> {
			Long endMilliSec = new Date().getTime();
			Long timeTaken = endMilliSec - startMilliSec;
			
			System.out.println("Completed Http Client Call to: " + request.getPath() + " time taken in ms: " + timeTaken);
			
			return r;
		});
	}

}
