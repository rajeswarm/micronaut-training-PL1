package com.learn.mn.services;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.UserInfo;

public interface UserInfoService {
	
	Publisher<UserInfo> getByUserId(String userId);
	Publisher<UserInfo> createUserInfo(UserInfo userInfo);
	Publisher<UserInfo> getAllUsers();

}
