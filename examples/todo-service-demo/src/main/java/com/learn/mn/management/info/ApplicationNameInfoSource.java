package com.learn.mn.management.info;

import java.util.HashMap;
import java.util.Map;

import org.reactivestreams.Publisher;

import io.micronaut.context.env.MapPropertySource;
import io.micronaut.context.env.PropertySource;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.management.endpoint.info.InfoSource;
import jakarta.inject.Singleton;

@Singleton
public class ApplicationNameInfoSource implements InfoSource {

	@Override
	public Publisher<PropertySource> getSource() {
		Map<String, String> applicationNameMap = new HashMap<>();
		applicationNameMap.put("Application Name", "TODO Service");

		return Publishers.just(new MapPropertySource("application-name", applicationNameMap));
	}

}
