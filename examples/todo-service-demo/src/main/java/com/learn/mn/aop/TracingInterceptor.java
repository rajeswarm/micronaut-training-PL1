package com.learn.mn.aop;

import java.util.Date;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;

@Singleton
@InterceptorBean(Traceable.class)
public class TracingInterceptor implements MethodInterceptor<Object, Object> {

	@Override
	public Object intercept(MethodInvocationContext<Object, Object> context) {

		// 1. Log start of method
		System.out.println("Starting method: " + context.getMethodName());
		Long startMilliSec = new Date().getTime();
		try {
			return context.proceed();
		} finally {

			Long endMilliSec = new Date().getTime();
			Long timeTaken = endMilliSec - startMilliSec;

			// 2. Log completion of method execution
			System.out.println("Completed method: " + context.getMethodName() + " time taken in ms: " + timeTaken);
		}

	}

}
