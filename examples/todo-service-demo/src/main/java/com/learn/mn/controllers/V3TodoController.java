
package com.learn.mn.controllers;

import com.learn.mn.services.TodoService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v3")
public class V3TodoController {
	
	@Inject
	@Named(value = "v3")
	private TodoService v3TodoService;
	
	@Inject
	private TodoService primaryTodoService;
	
	@Get(produces = MediaType.TEXT_PLAIN)
	public String getToDoList() {
		return v3TodoService.getTodoList();
	}

}
