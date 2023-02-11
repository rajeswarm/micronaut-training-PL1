package com.learn.mn.services;

import java.util.HashMap;
import java.util.Map;

import com.learn.mn.pojo.UserInfo;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class UserInfoServiceImpl implements UserInfoService {

	private Map<String, UserInfo> userInfoMap = new HashMap<>();

	@PostConstruct
	void onCreated() {
		for (int i = 1; i <= 3; i++) {
			String userId = String.valueOf(i);
			userInfoMap.put(userId, new UserInfo(userId, String.format("User " + userId)));
		}
	}

	@Override
	public UserInfo getByUserId(String userId) {
		return userInfoMap.get(userId);
	}

	@Override
	public UserInfo createUserInfo(UserInfo userInfo) {
		userInfoMap.put(userInfo.getUserId(), userInfo);
		return userInfo;
	}

}
