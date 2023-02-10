package com.learn.mn.config;

import java.time.Duration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties(value = "to-do.config")
public interface ImmutableTodoConfiguration {
	
	public String getTitlePrefix();
	public int getTitleMaxLength();
	public int getDescriptionMaxLength();
	public Duration getExpiryDuration();
}
