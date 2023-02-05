package com.learn.mn.services.versioned;

import com.learn.mn.services.TodoService;

import io.micronaut.context.annotation.Bean;
import jakarta.inject.Named;

@Bean
@Named(value = "v2")
public class V2TodoServiceImpl implements TodoService {

	@Override
	public String getTodoList() {
		return "My TO-DO list from service, version - v2";
	}

}
