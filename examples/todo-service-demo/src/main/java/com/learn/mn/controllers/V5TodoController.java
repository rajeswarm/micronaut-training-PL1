package com.learn.mn.controllers;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.reactivestreams.Publisher;

import com.learn.mn.client.UserServiceClient;
import com.learn.mn.pojo.TodoItemForUserResponse;
import com.learn.mn.pojo.TodoItemRequest;
import com.learn.mn.pojo.TodoItemResponse;
import com.learn.mn.pojo.UserInfo;
import com.learn.mn.services.TodoService;

import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v5")
public class V5TodoController {

	@Inject
	@Named("v4")
	protected TodoService v4TodoService;

	@Inject
	UserServiceClient userServiceClient;

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{id}")
	public Publisher<TodoItemResponse> getTodoById(@PathParam("id") int id) {
		TodoItemResponse response = new TodoItemResponse();

		var todoItemOptional = v4TodoService.getTodoItem(id);
		if (todoItemOptional.isPresent()) {
			var todoItem = todoItemOptional.get();
			response.setTodoItem(todoItem);
		}

		return Publishers.map(getUserInfo(response.getTodoItem().getUserId()), u -> {
			response.setUserInfo(u);
			return response;
		});
	}

	@Get(produces = MediaType.APPLICATION_JSON)
	public Publisher<TodoItemForUserResponse> getTodoForUser(@QueryParam("userId") String userId) {
		var todoItems = v4TodoService.getTodoListByUserId(userId);

		return Publishers.map(getUserInfo(userId), u -> {
			TodoItemForUserResponse todoItemForUserResponse = new TodoItemForUserResponse();
			todoItemForUserResponse.setTodoItems(todoItems);
			todoItemForUserResponse.setUserInfo(u);
			return todoItemForUserResponse;
		});
	}

	@Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)

	public Publisher<TodoItemResponse> createTodo(@Valid TodoItemRequest todoItemRequest, HttpRequest<?> request) {
		String correlationId = request.getAttribute("correlationId").toString();

		var createdTodoItem = v4TodoService.createTodoItem(todoItemRequest.getTodoItem());

		return Publishers.map(createUserInfo(todoItemRequest.getUserInfo(), correlationId), u -> {
			TodoItemResponse todoItemResponse = new TodoItemResponse();

			todoItemResponse.setTodoItem(createdTodoItem);
			todoItemResponse.setUserInfo(u);

			return todoItemResponse;
		});
	}

	private Publisher<UserInfo> getUserInfo(String userId) {
		return userServiceClient.getUserInfoById(userId);
	}

	private Publisher<UserInfo> createUserInfo(UserInfo userInfo, String correlationId) {
		return userServiceClient.createUserInfo(userInfo, correlationId);
	}
}
