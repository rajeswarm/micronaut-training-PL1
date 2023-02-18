package com.learn.mn.pojo;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class TodoItem {
	
	@NotBlank
	private String title;
	
	private String description;
	
	private String userId;

	private int id;
	
	private Date targetDate;
	
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
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
}
