package com.learn.mn.pojo;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TodoItemResponse {
	
	private TodoItem todoItem;
	private UserInfo userInfo;
	public TodoItem getTodoItem() {
		return todoItem;
	}
	public void setTodoItem(TodoItem todoItem) {
		this.todoItem = todoItem;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	

}
