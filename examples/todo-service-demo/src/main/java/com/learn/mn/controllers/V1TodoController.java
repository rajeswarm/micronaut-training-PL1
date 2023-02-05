
package com.learn.mn.controllers;

import javax.validation.Valid;

import com.learn.mn.pojo.TodoItem;
import com.learn.mn.services.TodoService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v1")
public class V1TodoController {
	
	@Inject
	@Named(value = "v1")
	private TodoService v1TodoService;
	
	@Inject
	private TodoService primaryTodoService;
	
	@Get(produces = MediaType.TEXT_PLAIN)
	public String getToDoList() {
		return primaryTodoService.getTodoList();
	}
	
	@Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.TEXT_PLAIN)
	public String createTodoItem(@Valid TodoItem todoItem) {
		return "Created TO-DO Item with Title: "+ todoItem.getTitle();
	}

}
