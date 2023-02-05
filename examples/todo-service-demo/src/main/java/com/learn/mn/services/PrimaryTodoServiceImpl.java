package com.learn.mn.services;

import java.util.concurrent.atomic.AtomicInteger;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.runtime.http.scope.RequestScope;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Singleton;

@RequestScope
@Primary
@Requires(property = "to-do.service.database", value = "false")
public class PrimaryTodoServiceImpl implements TodoService{
	
	private static AtomicInteger TotalInstanceCount = new AtomicInteger(0);
	private final int thisInstanceNumber;
	
	public PrimaryTodoServiceImpl() {
		TotalInstanceCount.addAndGet(1);
		
		thisInstanceNumber = TotalInstanceCount.get();
		
		System.out.println("PrimaryTodoServiceImpl Total Instance Count: "+ TotalInstanceCount.intValue());
	}

	@Override
	public String getTodoList() {
		return "My TO-DO list from primary service";
	}
	
	@PostConstruct
	void onCreated() {
		System.out.println("Created PrimaryTodoServiceImpl instance number: "+ thisInstanceNumber);
	}
	
	@PreDestroy
	void beforeDestroyed() {
		System.out.println("Destroying PrimaryTodoServiceImpl instance number: "+ thisInstanceNumber);
	}
}
