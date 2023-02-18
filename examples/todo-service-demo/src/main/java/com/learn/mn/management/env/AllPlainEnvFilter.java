
package com.learn.mn.management.env;

import javax.validation.constraints.NotNull;

import io.micronaut.management.endpoint.env.EnvironmentEndpointFilter;
import io.micronaut.management.endpoint.env.EnvironmentFilterSpecification;
import jakarta.inject.Singleton;

@Singleton
public class AllPlainEnvFilter implements EnvironmentEndpointFilter {

	@Override
	public void specifyFiltering(@NotNull EnvironmentFilterSpecification specification) {
		specification.maskNone();
	}

}
