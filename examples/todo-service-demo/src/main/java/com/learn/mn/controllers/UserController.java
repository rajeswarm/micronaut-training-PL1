package com.learn.mn.controllers;

import java.util.Optional;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.session.Session;

@Controller("/user")
public class UserController {
	
	private static final String USER_ID_SESSION_KEY = "USER_ID";

	@Post()
	public Response setUser(@QueryParam("userId") String userId, Session session) {
		session.put(USER_ID_SESSION_KEY, userId);
		return Response.ok().build();
	}
	
	@Get(produces = MediaType.TEXT_PLAIN)
	public Response getUser(Session session) {
		Optional<?> userId = session.get(USER_ID_SESSION_KEY);
		
		if(userId.isEmpty()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(userId.get()).build();
	}

}
