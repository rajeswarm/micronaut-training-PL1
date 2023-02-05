package com.learn.mn.services.versioned;

import com.learn.mn.services.TodoService;

import io.micronaut.context.annotation.Bean;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
@Named(value = "v1")
public class V1TodoServiceImpl implements TodoService {
	

	@Override
	public String getTodoList() {
		return "My TO-DO list from primary service, version v1";
	}
}
