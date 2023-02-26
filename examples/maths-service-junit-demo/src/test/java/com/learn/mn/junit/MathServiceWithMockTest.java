package com.learn.mn.junit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.learn.mn.services.MathService;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest(transactional = false)
public class MathServiceWithMockTest {

	@Inject
	MathService mathService;

	@ParameterizedTest
    @CsvSource({"2,4", "3,9"})
    void testComputeNumToSquare(Integer num, Integer square) {

        when(mathService.compute(num))
            .then(invocation -> Long.valueOf(Math.round(Math.pow(num, 2))).intValue());

        final Integer result = mathService.compute(num);

        Assertions.assertEquals(
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
