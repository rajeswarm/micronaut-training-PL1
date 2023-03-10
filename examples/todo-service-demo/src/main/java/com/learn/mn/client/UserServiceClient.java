package com.learn.mn.client;

import javax.validation.constraints.NotBlank;

import com.learn.mn.pojo.UserInfo;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Client(value = "user-service-demo")
@CircuitBreaker(attempts = "3", delay = "1s")
public interface UserServiceClient {

	@Get(uri = "/user-info/{userId}")
	Mono<UserInfo> getUserInfoById(@NotBlank @PathVariable("userId") String userId);

	@Post(uri = "/user-info")
	Mono<UserInfo> createUserInfo(UserInfo userInfo, @Header("X-CORRELATION-ID") String correlationId);

}
