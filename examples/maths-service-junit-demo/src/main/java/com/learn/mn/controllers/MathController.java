package com.learn.mn.controllers;

import com.learn.mn.services.MathService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/math")
public class MathController {

	@Inject
	MathService mathService;

	@Get(uri = "/compute/{number}", processes = MediaType.TEXT_PLAIN)
	String compute(Integer number) {
		return String.valueOf(mathService.compute(number));
	}
}
