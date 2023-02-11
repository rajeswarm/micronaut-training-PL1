package com.learn.mn.controllers;

import java.util.Collections;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.learn.mn.pojo.TodoItemForUserResponse;
import com.learn.mn.pojo.TodoItemRequest;
import com.learn.mn.pojo.TodoItemResponse;
import com.learn.mn.pojo.UserInfo;
import com.learn.mn.services.TodoService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Controller("/todo/v4")
public class V4TodoController {

	@Inject
	@Named("v4")
	protected TodoService v4TodoService;

	@Client("${user-service.url}")
	@Inject
	HttpClient userServiceClient;

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{id}")
	public TodoItemResponse getTodoById(@PathParam("id") int id, HttpRequest<?> request) {
		TodoItemResponse response = new TodoItemResponse();
		String correlationId = request.getAttribute("correlationId").toString();

		var todoItemOptional = v4TodoService.getTodoItem(id);
		if (todoItemOptional.isPresent()) {
			var todoItem = todoItemOptional.get();
			response.setTodoItem(todoItem);
			if (todoItem.getUserId() != null) {
				UserInfo userInfo = getUserInfoBlocking(todoItem.getUserId(), correlationId);
				response.setUserInfo(userInfo);
			}
		}

		return response;
	}

	@Get(produces = MediaType.APPLICATION_JSON)
	public TodoItemForUserResponse getTodoForUser(@QueryParam("userId") String userId, HttpRequest<?> request) {
		TodoItemForUserResponse todoItemForUserResponse = new TodoItemForUserResponse();
		String correlationId = request.getAttribute("correlationId").toString();

		var todoItems = v4TodoService.getTodoItemByUserId(userId);
		var userInfo = getUserInfoBlocking(userId, correlationId);

		todoItemForUserResponse.setTodoItems(todoItems);
		todoItemForUserResponse.setUserInfo(userInfo);

		return todoItemForUserResponse;
	}

	@Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public TodoItemResponse createTodo(@Valid TodoItemRequest todoItemRequest, HttpRequest<?> request) {
		TodoItemResponse todoItemResponse = new TodoItemResponse();
		String correlationId = request.getAttribute("correlationId").toString();

		var createdTodoItem = v4TodoService.createTodoItem(todoItemRequest.getTodoItem());
		todoItemResponse.setTodoItem(createdTodoItem);

		// get existing user
		var userInfo = todoItemRequest.getUserInfo();
		var existingUserInfo = getUserInfoBlocking(userInfo.getUserId(), correlationId);
		if (existingUserInfo == null || existingUserInfo.getUserId() == null) {
			// need to create new user info
			var createdUserInfo = createUserInfoBlocking(userInfo, correlationId);
			todoItemResponse.setUserInfo(createdUserInfo);
		} else {
			todoItemResponse.setUserInfo(existingUserInfo);
		}

		return todoItemResponse;
	}

	private UserInfo getUserInfoBlocking(String userId, String corrleationId) {
		String uri = UriBuilder.of("/user-info/{userId}").expand(Collections.singletonMap("userId", userId)).toString();

		UserInfo userInfo;
		try {
			userInfo = userServiceClient.toBlocking().retrieve(uri, UserInfo.class);
			
			//userInfo = userServiceClient.toBlocking().retrieve(HttpRequest.GET(uri).header("X-CORRELATION-ID", corrleationId), UserInfo.class);
			
		} catch (HttpClientResponseException e) {
			if (e.getStatus() == HttpStatus.NO_CONTENT || e.getStatus() == HttpStatus.NOT_FOUND) {
				return null;
			}

			throw new RuntimeException(e);
		}
		return userInfo;
	}

	private UserInfo createUserInfoBlocking(UserInfo userInfo, String corrleationId) {
		String uri = UriBuilder.of("/user-info").toString();
		UserInfo createdUserInfo = userServiceClient.toBlocking()
				.exchange(HttpRequest.POST(uri, userInfo).header("X-CORRELATION-ID", corrleationId), UserInfo.class)
				.body();
		return createdUserInfo;
	}

}
