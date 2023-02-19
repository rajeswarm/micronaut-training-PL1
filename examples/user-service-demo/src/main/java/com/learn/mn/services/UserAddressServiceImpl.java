package com.learn.mn.services;

import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import com.learn.mn.domain.UserAddressEntity;
import com.learn.mn.pojo.UserAddress;
import com.learn.mn.repositories.UserAddressRepository;

import io.micronaut.core.async.publisher.Publishers;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class UserAddressServiceImpl implements UserAddressService {

	@Inject
	UserAddressRepository userAddressRepository;

	@Override
	public Publisher<UserAddress> createAddress(UserAddress address) {
		UserAddressEntity userAddressEntity = new UserAddressEntity();

		addressToEntity(address, userAddressEntity);

		return Publishers.map(userAddressRepository.save(userAddressEntity), e -> {
			UserAddress createdAddress = new UserAddress();
			entityToAddress(createdAddress, e);
			return createdAddress;
		});
	}

	private void entityToAddress(UserAddress address, UserAddressEntity userAddressEntity) {
		address.setCity(userAddressEntity.getCity());
		address.setCountry(userAddressEntity.getCountry());
		address.setState(userAddressEntity.getState());
		address.setStreetAddress(userAddressEntity.getStreetAddress());
		address.setUserId(userAddressEntity.getUserId());
		address.setZipCode(userAddressEntity.getZipCode());
		address.setId(userAddressEntity.getId().toHexString());
	}

	private void addressToEntity(UserAddress address, UserAddressEntity userAddressEntity) {
		userAddressEntity.setCity(address.getCity());
		userAddressEntity.setCountry(address.getCountry());
		userAddressEntity.setState(address.getState());
		userAddressEntity.setStreetAddress(address.getStreetAddress());
		userAddressEntity.setUserId(address.getUserId());
		userAddressEntity.setZipCode(address.getZipCode());
	}

	@Override
	public Publisher<UserAddress> getAllAddress() {
		return Publishers.map(userAddressRepository.findAll(), e -> {
			UserAddress userAddress = new UserAddress();
			entityToAddress(userAddress, e);
			return userAddress;
		});
	}

	@Override
	public Publisher<UserAddress> getAddressByUserId(String userId) {
		return Publishers.map(userAddressRepository.findByUserId(userId), e -> {
			UserAddress userAddress = new UserAddress();
			entityToAddress(userAddress, e);
			return userAddress;
		});
	}

	@Override
	public Publisher<UserAddress> updateAddress(UserAddress address) {
		UserAddressEntity entity = new UserAddressEntity();
		addressToEntity(address, entity);

		entity.setId(new ObjectId(address.getId()));

		return Publishers.map(userAddressRepository.update(entity), updatedEntity -> {
			UserAddress updatedAddress = new UserAddress();
			entityToAddress(updatedAddress, updatedEntity);
			return updatedAddress;
		});
	}

	@Override
	public Publisher<Long> deleteAddress(String id) {
		return userAddressRepository.deleteById(new ObjectId(id));
	}

}
