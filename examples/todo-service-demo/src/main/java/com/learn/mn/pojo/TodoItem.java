package com.learn.mn.pojo;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TodoItem {
	
	@NotBlank
	private String title;
	
	private String description;
	
	private String userId;
	
	public TodoItem() {
	
	}
	
	public TodoItem(@NotBlank String title, String description, String userId) {
		super();
		this.title = title;
		this.description = description;
		this.userId = userId;
	}

	public TodoItem(@NotBlank String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
