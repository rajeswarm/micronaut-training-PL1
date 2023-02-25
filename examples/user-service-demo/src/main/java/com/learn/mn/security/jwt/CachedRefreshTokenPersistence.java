package com.learn.mn.security.jwt;

import java.util.Optional;

import org.reactivestreams.Publisher;

import io.micronaut.cache.CacheManager;
import io.micronaut.cache.SyncCache;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CachedRefreshTokenPersistence implements RefreshTokenPersistence {

	final SyncCache<Authentication> refreshTokenCache;

	@Inject
	CachedRefreshTokenPersistence(CacheManager<Authentication> cacheManager) {
		refreshTokenCache = cacheManager.getCache("refresh-token");
	}

	@Override
	public void persistToken(RefreshTokenGeneratedEvent event) {
		refreshTokenCache.put(event.getRefreshToken(), event.getAuthentication());
	}

	@Override
	public Publisher<Authentication> getAuthentication(String refreshToken) {
		Optional<Authentication> authenticationOptional = refreshTokenCache.get(refreshToken, Authentication.class);

		if (authenticationOptional.isPresent()) {
			return Publishers.just(authenticationOptional.get());
		}

		return Publishers.empty();
	}

}
