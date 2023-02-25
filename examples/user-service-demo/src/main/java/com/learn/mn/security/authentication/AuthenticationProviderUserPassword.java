package com.learn.mn.security.authentication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.reactivestreams.Publisher;

import io.micronaut.context.annotation.Context;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Context
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

	private static Set<UserIdentity> userIdentities = new HashSet<>();

	@PostConstruct
	void initUserIdentities() {
		userIdentities.add(new UserIdentity("usera", "passworda"));
		userIdentities.add(new UserIdentity("userb", "passwordb", "READER"));
		userIdentities.add(new UserIdentity("userc", "passwordc", "READER", "WRITER"));
		userIdentities.add(new UserIdentity("userd", "passwordd", "OTHER"));
	}

	@Override
	public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest,
			AuthenticationRequest<?, ?> authenticationRequest) {

		return Mono.<AuthenticationResponse>create(e -> {
			Optional<UserIdentity> optionalUserIdentity = getMatchedIdentity(authenticationRequest);
			if (optionalUserIdentity.isPresent()) {
				e.success(AuthenticationResponse.success(optionalUserIdentity.get().userName,
						optionalUserIdentity.get().roles));
			} else {
				e.error(AuthenticationResponse.exception());
			}
		});

	}

	private Optional<UserIdentity> getMatchedIdentity(AuthenticationRequest<?, ?> authenticationRequest) {
		return userIdentities.stream().filter(u -> authenticationRequest.getIdentity().equals(u.userName)
				&& authenticationRequest.getSecret().equals(u.password)).findFirst();
	}

	static class UserIdentity {

		private final String userName;
		private final String password;
		private final List<String> roles;

		UserIdentity(String userName, String password, String... roles) {
			this.userName = userName;
			this.password = password;

			if (roles != null) {
				this.roles = Arrays.asList(roles);
			} else {
				this.roles = new ArrayList<>();
			}
		}

		@Override
		public int hashCode() {
			return Objects.hash(userName);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserIdentity other = (UserIdentity) obj;
			return Objects.equals(userName, other.userName);
		}

	}

}
