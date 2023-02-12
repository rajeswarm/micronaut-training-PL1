package com.learn.mn.aop;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import jakarta.inject.Singleton;

@Singleton
@InterceptorBean(Traceable.class)
public class TracingInterceptor implements MethodInterceptor<Object, Object> {

	Logger logger = LoggerFactory.getLogger(TracingInterceptor.class);

	@Override
	public Object intercept(MethodInvocationContext<Object, Object> context) {

		// 1. Log start of method
		logger.info("Starting method: {}.{}", context.getTarget().getClass().getCanonicalName(),
				context.getMethodName());
		Long startMilliSec = new Date().getTime();
		try {
			return context.proceed();
		} finally {

			Long endMilliSec = new Date().getTime();
			Long timeTaken = endMilliSec - startMilliSec;

			// 2. Log completion of method execution
			logger.info("Completed method: {}.{}, time taken in ms: {}",
					context.getTarget().getClass().getCanonicalName(), context.getMethodName(), timeTaken);
		}

	}

}
