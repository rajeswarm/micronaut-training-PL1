
package com.learn.mn.controllers;

import com.learn.mn.services.TodoService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v2")
public class V2TodoController {
	
	@Inject
	@Named(value = "v2")
	private TodoService v2TodoService;
	
	@Inject
	private TodoService primaryTodoService;
	
	@Get(produces = MediaType.TEXT_PLAIN)
	public String getToDoList() {
		return v2TodoService.getTodoList();
	}

}
