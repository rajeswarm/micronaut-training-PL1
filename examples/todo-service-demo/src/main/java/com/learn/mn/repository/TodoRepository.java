package com.learn.mn.repository;

import java.util.List;

import com.learn.mn.domain.TodoEntity;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface TodoRepository extends CrudRepository<TodoEntity, Integer>{
	
	List<TodoEntity> getByUserId(String userId);
}
