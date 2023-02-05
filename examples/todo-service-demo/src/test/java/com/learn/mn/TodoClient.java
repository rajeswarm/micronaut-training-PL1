package com.learn.mn;

import org.reactivestreams.Publisher;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/todo")
public interface TodoClient {
	
	@Get(consumes = MediaType.TEXT_PLAIN)
	@SingleResult
	Publisher<String> getTodoList();
}
