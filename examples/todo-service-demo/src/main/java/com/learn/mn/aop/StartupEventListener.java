package com.learn.mn.aop;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

@Context
public class StartupEventListener {
	
	@EventListener
	public void onStartup(StartupEvent startupEvent) {
		System.out.println("Application started");
	}

}
