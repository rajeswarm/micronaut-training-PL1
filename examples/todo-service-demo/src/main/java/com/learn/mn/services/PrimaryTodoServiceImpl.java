package com.learn.mn.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.learn.mn.aop.Traceable;
import com.learn.mn.pojo.TodoItem;

import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;

@Singleton
@Primary
@Requires(property = "to-do.service.database", value = "false")
@Traceable
public class PrimaryTodoServiceImpl implements TodoService{
	
	private static AtomicInteger TotalInstanceCount = new AtomicInteger(0);
	private final int thisInstanceNumber;
	private final Map<Integer,TodoItem> todoItemsMap = new HashMap<>();
	
	public PrimaryTodoServiceImpl() {
		TotalInstanceCount.addAndGet(1);
		
		thisInstanceNumber = TotalInstanceCount.get();
		
		System.out.println("PrimaryTodoServiceImpl Total Instance Count: "+ TotalInstanceCount.intValue());
	}

	@Override
	public String getTodoList() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			
		}
		
		return "My TO-DO list from primary service";
	}
	
	@Override
	public Optional<TodoItem> getTodoItem(int id) {
		if(todoItemsMap.containsKey(id)) {
			return Optional.of(todoItemsMap.get(id));
		}
		
		return Optional.empty();
	}
	
	@Override
	public TodoItem createTodoItem(TodoItem todoItem) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
		
		todoItemsMap.put(todoItemsMap.size() +1, todoItem);
		return todoItem;
	}
	
	@PostConstruct
	void onCreated() {
		System.out.println("Created PrimaryTodoServiceImpl instance number: "+ thisInstanceNumber);
		
		todoItemsMap.put(1, new TodoItem("Title 1", "Description 1"));
		todoItemsMap.put(2, new TodoItem("Title 2", "Description 2"));
		todoItemsMap.put(3, new TodoItem("Title 3", "Description 3"));
	}
	
	@PreDestroy
	void beforeDestroyed() {
		System.out.println("Destroying PrimaryTodoServiceImpl instance number: "+ thisInstanceNumber);
	}
}
