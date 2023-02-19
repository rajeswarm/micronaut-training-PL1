package com.learn.mn.controllers;

import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.UserInfo;
import com.learn.mn.services.UserInfoService;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/user-info")
public class UserInfoController {
	
	@Inject
	UserInfoService userInfoService;
	
	@Get(produces = MediaType.APPLICATION_JSON)
	public Publisher<UserInfo> getAllUsers() {
		return userInfoService.getAllUsers();
	}
	
	/**
	 * Returns userInfo based on userId parameter
	 * @param userId
	 * @return
	 */
	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{userId}")
	@SingleResult
	public Publisher<UserInfo> getByUserId(@PathParam("userId") String userId) {
		return userInfoService.getByUserId(userId);
	}
	
	/**
	 * Creates a new userInfo object
	 * @param userInfo
	 * @return
	 */
	@Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	@SingleResult
	public Publisher<UserInfo> createUserInfo(@Valid UserInfo userInfo) {
		return userInfoService.createUserInfo(userInfo);
	}

}
