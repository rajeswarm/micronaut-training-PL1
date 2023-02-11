package com.learn.mn.services;

import com.learn.mn.pojo.UserInfo;

public interface UserInfoService {
	
	UserInfo getByUserId(String userId);
	UserInfo createUserInfo(UserInfo userInfo);

}
