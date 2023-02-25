package com.learn.mn.conference.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.learn.mn.conference.pojo.Conference;

import jakarta.inject.Singleton;

@Singleton
public class ConferenceService {

	private static final List<Conference> CONFERENCES = Arrays.asList(
            new Conference("Mumbai DevOps"),
            new Conference("Bengaluru Mobile DevCon"),
            new Conference("Micronaut Summit"),
            new Conference("Quarkus Conference"),
            new Conference("Java Code One"),
            new Conference("CommitConf"),
            new Conference("Codemotion Hyderabad")
    );
	
	public Conference randomConf() { 
        return CONFERENCES.get(new Random().nextInt(CONFERENCES.size()));
    }
}
