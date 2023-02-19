package com.learn.mn.repositories;

import com.learn.mn.domain.UserInfoEntity;

import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface UserInfoRepository extends ReactiveStreamsCrudRepository<UserInfoEntity, String> {

}
