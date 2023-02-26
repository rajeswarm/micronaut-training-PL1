package com.learn.mn.service;

import jakarta.inject.Singleton;

@Singleton
class MathServiceImpl implements MathService {

	@Override
	public Integer compute(Integer num) {
		return num * 4; // should never be called
	}
}
