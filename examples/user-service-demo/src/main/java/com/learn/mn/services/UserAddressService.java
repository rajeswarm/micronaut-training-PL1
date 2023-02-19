package com.learn.mn.services;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.UserAddress;

public interface UserAddressService {

	Publisher<UserAddress> createAddress(UserAddress address);
	Publisher<UserAddress> getAllAddress();
	Publisher<UserAddress> getAddressByUserId(String userId);
	Publisher<UserAddress> updateAddress(UserAddress address);
	Publisher<Long> deleteAddress(String id);
}
