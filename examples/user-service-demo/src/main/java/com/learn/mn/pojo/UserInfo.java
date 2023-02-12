package com.learn.mn.pojo;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.annotation.Introspected;

/**
 * Represents profile information of user
 *
 */
@Introspected
public class UserInfo {

	/**
	 * User Id of the User
	 */
	private String userId;
	
	
	/**
	 * Name of the User
	 */
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
