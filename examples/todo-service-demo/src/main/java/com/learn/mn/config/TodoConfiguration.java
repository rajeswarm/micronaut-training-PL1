package com.learn.mn.config;

import java.time.Duration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties(value = "to-do.config")
public class TodoConfiguration {
	
	private String titlePrefix;
	private int titleMaxLength;
	private int descriptionMaxLength;
	private Duration expiryDuration;

	public String getTitlePrefix() {
		return titlePrefix;
	}
	public void setTitlePrefix(String titlePrefix) {
		this.titlePrefix = titlePrefix;
	}
	public int getTitleMaxLength() {
		return titleMaxLength;
	}
	public void setTitleMaxLength(int titleMaxLength) {
		this.titleMaxLength = titleMaxLength;
	}
	public int getDescriptionMaxLength() {
		return descriptionMaxLength;
	}
	public void setDescriptionMaxLength(int descriptionMaxLength) {
		this.descriptionMaxLength = descriptionMaxLength;
	}
	public Duration getExpiryDuration() {
		return expiryDuration;
	}
	public void setExpiryDuration(Duration expiryDuration) {
		this.expiryDuration = expiryDuration;
	}
}
