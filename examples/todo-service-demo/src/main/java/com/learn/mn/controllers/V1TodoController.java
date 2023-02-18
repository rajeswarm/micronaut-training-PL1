
package com.learn.mn.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learn.mn.pojo.TodoItem;
import com.learn.mn.services.TodoService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v1")
public class V1TodoController {

	Logger logger = LoggerFactory.getLogger(V1TodoController.class);

	@Inject
	@Named(value = "v1")
	private TodoService v1TodoService;

	@Inject
	private TodoService primaryTodoService;

	@Get(produces = MediaType.APPLICATION_JSON)
	@ExecuteOn(value = TaskExecutors.IO)
	public List<TodoItem> getToDoList() {
		return primaryTodoService.getTodoList();
	}

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{id}")
	public Response getTodoItemById(@PathParam("id") int id, HttpRequest<?> request) {

		String correlationId = request.getAttribute("correlationId").get().toString();
		System.out.println("Correlation Id in Request Attribute: " + correlationId);

		Optional<TodoItem> todoItemOptional = primaryTodoService.getTodoItem(id);

		if (todoItemOptional.isPresent()) {
			return Response.ok(todoItemOptional.get()).build();
		}

		return Response.status(Status.NOT_FOUND).build();
	}

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/by-user")
	@ExecuteOn(value = TaskExecutors.IO)
	public List<TodoItem> getToDoListByUserId(@QueryParam("userId") String userId) {
		return primaryTodoService.getTodoListByUserId(userId);
	}

	@Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@ExecuteOn(value = TaskExecutors.IO)
	public TodoItem createTodoItem(@Valid TodoItem todoItem) {

		logger.info("Executing V1TodoController.createTodoItem() method");

		try {
			var createdTodoItem = primaryTodoService.createTodoItem(todoItem);
			return createdTodoItem;
		} finally {
			logger.info("Completed V1TodoController.createTodoItem() method");
		}
	}

	@Put(produces = MediaType.APPLICATION_JSON)
	@ExecuteOn(value = TaskExecutors.IO)
	public TodoItem updateTodoItem(@Body TodoItem todoItem) {
		return primaryTodoService.updateTodoItem(todoItem);
	}

	@Delete(produces = MediaType.TEXT_PLAIN, uri = "/{id}")
	@ExecuteOn(value = TaskExecutors.IO)
	public String deleteTodoItem(@PathParam("id") int id) {
		primaryTodoService.deleteTodoItem(id);
		return "Todo Item is deleted";
	}

}
