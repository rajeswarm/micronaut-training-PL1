package com.learn.mn.pojo;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TodoItem {
	
	@NotBlank
	private String title;
	
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
