package com.learn.mn.controllers;

import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;


@MicronautTest
public class TodoControllerSpec {
	
	@Inject
	EmbeddedServer server;
	
	@Inject
	@Client("/")
	HttpClient client;
	
	@Test
    void testTodoResponse() {
        String response = client.toBlocking() // 
                .retrieve(HttpRequest.GET("/todo"));
        assertEquals("My TO-DO List", response); // 
    }

}
