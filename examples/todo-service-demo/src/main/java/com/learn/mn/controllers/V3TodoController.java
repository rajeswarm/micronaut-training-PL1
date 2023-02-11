
package com.learn.mn.controllers;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.reactivestreams.Publisher;

import com.learn.mn.errors.NotImplementedException;
import com.learn.mn.pojo.TodoItem;
import com.learn.mn.services.TodoService;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Controller("/todo/v3")
public class V3TodoController {

	private Scheduler scheduler;

	public V3TodoController(@Named(TaskExecutors.IO) ExecutorService executorService) {
		scheduler = Schedulers.fromExecutorService(executorService);
	}

	@Inject
	@Named(value = "v3")
	private TodoService v3TodoService;

	@Inject
	private TodoService primaryTodoService;

	@Get(produces = MediaType.TEXT_PLAIN)
	public String getToDoList() {
		return v3TodoService.getTodoList();
	}

	@Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@SingleResult
	public Publisher<TodoItem> createTodo(@Body @Valid TodoItem todoItem) {
		return Mono.fromCallable(() -> primaryTodoService.createTodoItem(todoItem)).subscribeOn(scheduler);
	}
	
	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{id}")
	public Response getTodoItemById(@PathParam("id") int id, HttpRequest<?> request) {
		
		Optional<TodoItem> todoItemOptional = v3TodoService.getTodoItem(id);
		
		if(todoItemOptional.isPresent()) {
			return Response.ok(todoItemOptional.get()).build();
		}
		
		return Response.status(Status.NOT_FOUND).build();
	}
	
	/*
	 * @Error public Response handleNotImplementedError(HttpRequest<?> request,
	 * NotImplementedException notImplementedException) { return
	 * Response.status(Status.BAD_REQUEST).entity(notImplementedException.getMessage
	 * ()).build(); }
	 */

}
