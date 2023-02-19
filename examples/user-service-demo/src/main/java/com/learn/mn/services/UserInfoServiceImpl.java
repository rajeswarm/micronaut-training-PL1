package com.learn.mn.services;

import javax.transaction.Transactional;

import org.reactivestreams.Publisher;

import com.learn.mn.domain.UserInfoEntity;
import com.learn.mn.pojo.UserInfo;
import com.learn.mn.repositories.UserInfoRepository;

import io.micronaut.core.async.publisher.Publishers;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class UserInfoServiceImpl implements UserInfoService {

	@Inject
	UserInfoRepository userInfoRepository;

	@Override
	public Publisher<UserInfo> getByUserId(String userId) {
		return Publishers.map(userInfoRepository.findById(userId), e -> {
			return mapEntityToPojo(e);
		});
	}

	private UserInfo mapEntityToPojo(UserInfoEntity e) {
		UserInfo userInfo = new UserInfo(e.getUserId(), e.getName());
		return userInfo;
	}

	@Override
	public Publisher<UserInfo> createUserInfo(UserInfo userInfo) {
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		userInfoEntity.setUserId(userInfo.getUserId());
		userInfoEntity.setName(userInfo.getName());

		return Publishers.map(userInfoRepository.save(userInfoEntity), e -> {
			return mapEntityToPojo(e);
		});
	}

	@Override
	public Publisher<UserInfo> getAllUsers() {
		return Publishers.map(userInfoRepository.findAll(), e -> {
			return mapEntityToPojo(e);
		});
	}

}
