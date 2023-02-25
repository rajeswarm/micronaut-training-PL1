package com.learn.mn.controllers;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.reactivestreams.Publisher;

import com.learn.mn.pojo.UserAddress;
import com.learn.mn.services.UserAddressService;

import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

@Controller("/user-address")
@Secured(SecurityRule.IS_ANONYMOUS)
public class UserAddressController {

	@Inject
	UserAddressService userAddressService;
	
	@Get(produces = MediaType.APPLICATION_JSON)
	public Publisher<UserAddress> getAllUserAddress() {
		return userAddressService.getAllAddress();
	}
	
	@Get(produces = MediaType.APPLICATION_JSON, uri = "/by-user")
	@SingleResult
	public Publisher<UserAddress> getUserAddressByUserId(@QueryParam("userId") String userId) {
		return userAddressService.getAddressByUserId(userId);
	}
	

	@Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@SingleResult
	public Publisher<UserAddress> createUserAddress(@Body UserAddress address) {
		return userAddressService.createAddress(address);
	}
	
	@Put(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	@SingleResult
	public Publisher<UserAddress> updateUserAddress(@Body UserAddress address) {
		return userAddressService.updateAddress(address);
	}
	
	@Delete(produces = MediaType.TEXT_PLAIN, uri = "/{id}")
	@SingleResult
	public Publisher<String> deleteUserAddress(@PathParam("id") String id) {
		return Publishers.map(userAddressService.deleteAddress(id), c -> {
			return "Deleted Address with Id: " + id;
		});
	}
}
