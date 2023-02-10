package com.learn.mn.controllers;

import java.util.Optional;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.learn.mn.config.ImmutableTodoConfiguration;
import com.learn.mn.config.TodoConfiguration;

import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.env.Environment;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/config")
public class ConfigController {

	@Inject
	Environment environment;

	@Value("${to-do.prop-a}")
	protected String propA;
	
	@Property(name = "to-do.prop-b")
	protected String propB;
	
	@Inject
	protected TodoConfiguration todoConfiguration;
	
	@Inject
	protected ImmutableTodoConfiguration immutableTodoConfiguration;

	@Get(produces = MediaType.TEXT_PLAIN)
	public Response getConfigValue(@QueryParam("prop") String prop) {

		Optional<String> configValueOptional = environment.getProperty(prop, String.class);
		
		if (configValueOptional.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(configValueOptional.get()).build();
	}
	
	@Get(produces = MediaType.TEXT_PLAIN,uri = "/prop-a")
	public Response getPropertyA() {
		return Response.ok(propA).build();
	}
	
	@Get(produces = MediaType.TEXT_PLAIN,uri = "/prop-b")
	public Response getPropertyB() {
		return Response.ok(propB).build();
	}
	
	@Get(produces = MediaType.APPLICATION_JSON,uri = "/todo")
	public Response getTodoConfig() {
		return Response.ok(todoConfiguration).build();
	}
	
	@Get(produces = MediaType.APPLICATION_JSON,uri = "/todo/immutable")
	public Response getImmutableTodoConfig() {
		return Response.ok(immutableTodoConfiguration).build();
	}

}
