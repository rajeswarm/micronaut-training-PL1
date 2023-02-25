package com.learn.mn.conference.controller;

import com.learn.mn.conference.pojo.Conference;
import com.learn.mn.conference.service.ConferenceService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

@Controller("/conference")
public class ConferenceController {
	
	@Inject
	ConferenceService conferenceService;
	
	@Get(produces = MediaType.APPLICATION_JSON, uri = "/random")
	public Conference getRandomConference() {
		return conferenceService.randomConf();
	}

}
