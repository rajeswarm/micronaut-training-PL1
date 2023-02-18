
package com.learn.mn.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.learn.mn.pojo.TodoItem;
import com.learn.mn.services.TodoService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v2")
public class V2TodoController {
	

	@Inject
	@Named(value = "v2")
	private TodoService v2TodoService;

	@Inject
	private TodoService primaryTodoService;

	@Get(produces = MediaType.APPLICATION_JSON)
	public List<TodoItem> getToDoList() {
		return v2TodoService.getTodoList();
	}

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/by-id")
	public Response getTodoItemById(HttpRequest<?> httpRequest) {
		int id = Integer.valueOf(httpRequest.getParameters().get("id"));

		String correlationId = UUID.randomUUID().toString();

		Optional<TodoItem> todoItemOptional = primaryTodoService.getTodoItem(id);

		ResponseBuilder responseBuilder;

		if (todoItemOptional.isPresent()) {
			responseBuilder = Response.ok(todoItemOptional.get());
		} else {
			responseBuilder = Response.status(Status.NOT_FOUND);
		}

		System.out.println("Correlation Id: " + correlationId);

		return responseBuilder.header("correlationId", correlationId).build();
	}
	
	@Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public Response createTodo(@Body @Valid TodoItem todoItem) {
		System.out.println("Created V2 TODO Item with title: "+ todoItem.getTitle());
		return Response.ok(todoItem).build();
	}

}
