package com.learn.mn.junit;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class MathsServiceJunitDemoTest {

    @Inject
    EmbeddedApplication<?> application;
    
    @Inject
    EmbeddedServer server;
    
    @Test
    void testServerRunning() {
    	Assertions.assertTrue(server.isRunning());
    }
    

    @Test
    void testAppRunning() {
        Assertions.assertTrue(application.isRunning());
    }

}
