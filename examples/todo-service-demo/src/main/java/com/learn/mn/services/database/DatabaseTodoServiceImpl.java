package com.learn.mn.services.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.learn.mn.domain.TodoEntity;
import com.learn.mn.pojo.TodoItem;
import com.learn.mn.repository.TodoRepository;
import com.learn.mn.services.TodoService;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Inject;

@Bean
@Requires(property = "to-do.service.database", value = "true")
@Primary
public class DatabaseTodoServiceImpl implements TodoService {

	@Inject
	TodoRepository todoRepository;

	@Override
	public List<TodoItem> getTodoList() {

		List<TodoItem> items = new ArrayList<>();

		for (TodoEntity entity : todoRepository.findAll()) {
			TodoItem todoItem = entityToTodoItem(entity);
			items.add(todoItem);
		}

		return items;
	}

	@Override
	public Optional<TodoItem> getTodoItem(int id) {
		Optional<TodoEntity> todoEntityOptional = todoRepository.findById(id);
		if (todoEntityOptional.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(entityToTodoItem(todoEntityOptional.get()));
	}

	public List<TodoItem> getTodoListByUserId(String userId) {
		List<TodoItem> items = new ArrayList<>();

		var entities = todoRepository.getByUserId(userId);

		for (TodoEntity entity : entities) {
			TodoItem todoItem = entityToTodoItem(entity);
			items.add(todoItem);
		}

		return items;
	}

	private TodoItem entityToTodoItem(TodoEntity entity) {
		TodoItem todoItem = new TodoItem();
		todoItem.setDescription(entity.getDescription());
		todoItem.setTargetDate(entity.getTargetDate());
		todoItem.setTitle(entity.getName());
		todoItem.setUserId(entity.getUserId());
		todoItem.setId(entity.getId());

		return todoItem;
	}

	@Override
	@Transactional
	public TodoItem createTodoItem(TodoItem todoItem) {
		TodoEntity todoEntity = new TodoEntity();
		todoItemToEntity(todoItem, todoEntity);

		TodoEntity savedEntity = todoRepository.save(todoEntity);
		todoItem.setId(savedEntity.getId());

		return todoItem;
	}

	@Override
	@Transactional
	public TodoItem updateTodoItem(TodoItem todoItem) {
		TodoEntity todoEntity = new TodoEntity();
		todoItemToEntity(todoItem, todoEntity);

		todoEntity.setId(todoItem.getId());
		
		TodoEntity updatedEntity = todoRepository.update(todoEntity);
		return entityToTodoItem(updatedEntity);
	}

	private void todoItemToEntity(TodoItem todoItem, TodoEntity todoEntity) {
		todoEntity.setDescription(todoItem.getDescription());
		todoEntity.setName(todoItem.getTitle());

		if (todoItem.getTargetDate() == null) {
			todoEntity.setTargetDate(new Date());
		} else {
			todoEntity.setTargetDate(todoItem.getTargetDate());
		}
		todoEntity.setUserId(todoItem.getUserId());
	}
	
	@Override
	@Transactional
	public void deleteTodoItem(int id) {
		todoRepository.deleteById(id);
	}
}
