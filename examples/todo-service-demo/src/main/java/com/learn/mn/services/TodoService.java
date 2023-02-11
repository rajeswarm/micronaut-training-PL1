package com.learn.mn.services;

import java.util.List;
import java.util.Optional;

import com.learn.mn.errors.NotImplementedException;
import com.learn.mn.pojo.TodoItem;

public interface TodoService {
	public String getTodoList();

	default Optional<TodoItem> getTodoItem(int id) {
		throw new NotImplementedException("getTodoItem method is not implemented");
	}
	
	default TodoItem createTodoItem(TodoItem todoItem) {
		throw new NotImplementedException("createTodoItem method is not implemented");
	}
	
	default List<TodoItem> getTodoItemByUserId(String userId) {
		throw new NotImplementedException("getTodoItemByUserId method is not implemented");
	}
}
