package com.learn.mn.services.versioned;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learn.mn.pojo.TodoItem;
import com.learn.mn.services.TodoService;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named("v4")
public class V4TodoServiceImpl implements TodoService {

	private final Map<Integer, TodoItem> todoItemsMap = new HashMap<>();

	@PostConstruct
	void onCreated() {
		todoItemsMap.put(1, new TodoItem("User 1 Todo 1", "Description 1", "1"));
		todoItemsMap.put(2, new TodoItem("User 1 Todo 2", "Description 2", "1"));
		todoItemsMap.put(3, new TodoItem("User 2 Todo 3", "Description 3", "2"));
		todoItemsMap.put(4, new TodoItem("User 2 Todo 4", "Description 4", "2"));
		todoItemsMap.put(5, new TodoItem("User 3 Todo 5", "Description 5", "3"));
	}

	@Override
	public List<TodoItem> getTodoList() {
		return todoItemsMap.values().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<TodoItem> getTodoItem(int id) {
		if (todoItemsMap.containsKey(id)) {
			return Optional.of(todoItemsMap.get(id));
		}

		return Optional.empty();
	}

	@Override
	public List<TodoItem> getTodoListByUserId(String userId) {
		return todoItemsMap.values().stream().filter(t -> t.getUserId().equals(userId)).collect(Collectors.toList());
	}
	
	@Override
	public TodoItem createTodoItem(TodoItem todoItem) {
		todoItemsMap.put(todoItemsMap.size() +1, todoItem);
		return todoItem;
	}
}
