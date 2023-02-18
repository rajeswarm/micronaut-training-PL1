package com.learn.mn.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODO_TBL")
public class TodoEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "TODO_ID")
	private int id;
	
	@Column(name = "TODO_NAME")
	private String name;
	
	@Column(name = "TODO_DESC")
	private String description;
	
	@Column(name = "TARGET_DATE")
	private Date targetDate;
	
	@Column(name = "USER_ID")
	private String userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
