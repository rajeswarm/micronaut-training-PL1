package com.learn.mn.pojo;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class UserInfo {

	private String userId;
	@NotBlank
	private String name;

	public UserInfo() {
	}

	public UserInfo(String userId, @NotBlank String name) {
		super();
		this.userId = userId;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
