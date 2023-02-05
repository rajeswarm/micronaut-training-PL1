
package com.learn.mn.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/todo")
public class TodoController {
	
	@Get(produces = MediaType.TEXT_PLAIN)
	public String getToDoList() {
		return "My TO-DO List";
	}

}
