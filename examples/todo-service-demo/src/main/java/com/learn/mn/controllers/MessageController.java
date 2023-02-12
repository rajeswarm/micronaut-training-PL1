package com.learn.mn.controllers;

import java.util.Locale;

import javax.ws.rs.PathParam;

import com.learn.mn.pojo.LocalizedMessage;

import io.micronaut.context.MessageSource;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/message")
public class MessageController {

	@Inject
	protected MessageSource messageSource;

	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{id}")
	public LocalizedMessage getMessage(@PathParam("id") String id, Locale locale) {

		String text = messageSource.getMessage(id, locale).orElseGet(() -> "NA");
		
		LocalizedMessage localizedMessage = new LocalizedMessage(id, text, locale.getDisplayName());

		return localizedMessage;
	}

}
