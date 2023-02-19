package com.learn.mn.repositories;

import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;

import com.learn.mn.domain.UserAddressEntity;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;

@MongoRepository
public interface UserAddressRepository extends ReactiveStreamsCrudRepository<UserAddressEntity, ObjectId> {
	
	Publisher<UserAddressEntity> findByUserId(String userId);

}
