package com.learn.mn.services.versioned;

import com.learn.mn.services.TodoService;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Named;

@Factory
public class V3TodoServiceFactory {
	
	@Bean
	@Named("v3")
	public TodoService getV3TodoService() {
		return new V3TodoServiceImpl();
	}

}
