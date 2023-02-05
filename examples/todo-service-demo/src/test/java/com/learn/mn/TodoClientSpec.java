package com.learn.mn;

import org.junit.jupiter.api.Test;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

@MicronautTest
public class TodoClientSpec {

	@Inject
	TodoClient toDoClient;
	
	@Test
	void testTodoClientResponse() {
		String actual = Mono.from(toDoClient.getTodoList()).block();
		assertEquals("My TO-DO List", actual);
	}

}
