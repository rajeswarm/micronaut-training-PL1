package com.learn.mn.services.versioned;

import com.learn.mn.services.TodoService;

import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.context.annotation.Bean;
import jakarta.inject.Named;

@Bean
@Named(value = "v2")
@Cacheable(cacheNames = "todo-cache")
public class V2TodoServiceImpl implements TodoService {
}
