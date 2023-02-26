package com.learn.mn.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.learn.mn.services.MathService;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest(transactional = false)
public class MathServiceTest {

	@Inject
	MathService mathService;

	@Test
	void testCompute() {
		final Integer actual = mathService.compute(5);
		Assertions.assertEquals(20, actual);
	}

	@ParameterizedTest
	@CsvSource({ "2,8", "3,12" })
	void testComputeNumToFourTimes(Integer num, Integer square) {
		final Integer result = mathService.compute(num);

		Assertions.assertEquals(square, result);
	}
}
