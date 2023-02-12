package com.learn.mn.controllers;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.learn.mn.pojo.UserInfo;
import com.learn.mn.services.UserInfoService;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/user-info")
public class UserInfoController {
	
	@Inject
	UserInfoService userInfoService;
	
	/**
	 * Returns userInfo based on userId parameter
	 * @param userId
	 * @return
	 */
	@Get(produces = MediaType.APPLICATION_JSON, uri = "/{userId}")
	public Response getByUserId(@PathParam("userId") String userId) {
		UserInfo userInfo = userInfoService.getByUserId(userId);
		if(userInfo != null) {
			return Response.ok(userInfo).build();
		}
		
		return Response.noContent().build();
	}
	
	/**
	 * Creates a new userInfo object
	 * @param userInfo
	 * @return
	 */
	@Post(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public UserInfo createUserInfo(@Valid UserInfo userInfo) {
		return userInfoService.createUserInfo(userInfo);
	}

}
