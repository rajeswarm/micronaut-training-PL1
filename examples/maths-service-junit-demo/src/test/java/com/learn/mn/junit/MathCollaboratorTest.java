package com.learn.mn.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.learn.mn.services.MathService;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest
public class MathCollaboratorTest {

	@Inject
	MathService mathService;

	@Inject
	@Client("/")
	HttpClient client;
	
    @ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquare(Integer num, Integer square) {

        when( mathService.compute(num) )
            .then(invocation -> Long.valueOf(Math.round(Math.pow(num, 2))).intValue());

        final Integer result = client.toBlocking().retrieve(HttpRequest.GET("/math/compute/" + num), Integer.class);

        assertEquals(
                square,
                result
        );
        verify(mathService).compute(num);
    }
    
    @MockBean(MathService.class)
    MathService mathService() {
        return mock(MathService.class);
    }

}
