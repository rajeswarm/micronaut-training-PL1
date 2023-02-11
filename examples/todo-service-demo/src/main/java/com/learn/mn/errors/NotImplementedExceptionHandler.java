
package com.learn.mn.errors;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor;
import jakarta.inject.Singleton;

@Singleton
@Produces
@Requires(classes = { NotImplementedException.class, ExceptionHandler.class })
public class NotImplementedExceptionHandler implements ExceptionHandler<NotImplementedException, HttpResponse<?>> {

	private final ErrorResponseProcessor<?> errorResponseProcessor;

	NotImplementedExceptionHandler(ErrorResponseProcessor<?> errorResponseProcessor) {
		this.errorResponseProcessor = errorResponseProcessor;
	}

	@Override
	public HttpResponse<?> handle(HttpRequest request, NotImplementedException exception) {
		return errorResponseProcessor.processResponse(
				ErrorContext.builder(request).cause(exception).errorMessage("This operation is not supported").build(),
				HttpResponse.badRequest());
	}

}
