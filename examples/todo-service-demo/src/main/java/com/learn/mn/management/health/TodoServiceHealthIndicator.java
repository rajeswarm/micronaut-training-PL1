package com.learn.mn.management.health;

import java.util.HashMap;
import java.util.Map;

import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.AbstractHealthIndicator;
import io.micronaut.management.health.indicator.annotation.Liveness;
import jakarta.inject.Singleton;

@Liveness
@Singleton
public class TodoServiceHealthIndicator extends AbstractHealthIndicator<Map<String, String>> {

	@Override
	protected Map<String, String> getHealthInformation() {
		healthStatus = HealthStatus.UP;
		
		HashMap<String, String> map = new HashMap<>();
		map.put("Service Status", "RUNNING");

		return map;
	}

	@Override
	protected String getName() {
		return "TodoServiceHealth";
	}

}
