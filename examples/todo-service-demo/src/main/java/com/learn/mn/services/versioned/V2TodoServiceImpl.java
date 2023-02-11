package com.learn.mn.services.versioned;

import com.learn.mn.services.TodoService;

import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.context.annotation.Bean;
import jakarta.inject.Named;

@Bean
@Named(value = "v2")
@Cacheable(cacheNames = "todo-cache")
public class V2TodoServiceImpl implements TodoService {

	@Override
	public String getTodoList() {
		System.out.println("Executing V2TodoServiceImpl.getTodoList() method");
		return "My TO-DO list from service, version - v2";
	}

}
