package com.learn.mn.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {

	@Get(produces = MediaType.TEXT_PLAIN, uri = "/{name}")
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
