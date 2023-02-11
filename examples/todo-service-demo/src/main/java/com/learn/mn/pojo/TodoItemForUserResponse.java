package com.learn.mn.pojo;

import java.util.List;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TodoItemForUserResponse {

	private List<TodoItem> todoItems;
	private UserInfo userInfo;

	public List<TodoItem> getTodoItems() {
		return todoItems;
	}

	public void setTodoItems(List<TodoItem> todoItems) {
		this.todoItems = todoItems;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
