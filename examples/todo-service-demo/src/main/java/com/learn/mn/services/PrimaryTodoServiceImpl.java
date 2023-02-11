package com.learn.mn.services;

import java.util.concurrent.atomic.AtomicInteger;

import com.learn.mn.aop.Traceable;

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
	
	@PostConstruct
	void onCreated() {
		System.out.println("Created PrimaryTodoServiceImpl instance number: "+ thisInstanceNumber);
	}
	
	@PreDestroy
	void beforeDestroyed() {
		System.out.println("Destroying PrimaryTodoServiceImpl instance number: "+ thisInstanceNumber);
	}
}
