package com.learn.mn.management.custom;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Delete;
import io.micronaut.management.endpoint.annotation.Endpoint;
import io.micronaut.management.endpoint.annotation.Read;
import io.micronaut.management.endpoint.annotation.Write;

@Endpoint(id = "greetings", defaultSensitive = false)
public class GreetingsEndpoint {

	private String greetingsMessage = "Welcome to TODO Service App !!!";

	@Read
	public String getGreetingsMessage() {
		return greetingsMessage;
	}
	
	@Write(consumes = MediaType.TEXT_PLAIN)
	public void updateGreetingsMessage(String message) {
		this.greetingsMessage = message;
	}
	
	@Delete
	public void deleteGreetingsMessage() {
		this.greetingsMessage = "";
	}

}
