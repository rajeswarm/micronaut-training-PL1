package com.learn.mn.restassured;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;

@MicronautTest
public class RestAssuredTest {

	@Test
    void testHelloWorld(RequestSpecification spec) {
        spec
            .when().get("/hello/john")
            .then().statusCode(200)
                .body(is("Hello john"));
    }
}
