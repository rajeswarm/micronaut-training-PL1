package com.learn.mn.services.database;

import com.learn.mn.services.TodoService;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;

@Bean
@Requires(property = "to-do.service.database", value = "true")
@Primary
public class DatabaseTodoServiceImpl implements TodoService{

	@Override
	public String getTodoList() {
		return "My TO-DO list from database service";
	}
}
