package com.learn.mn.services.versioned;

import com.learn.mn.services.TodoService;

public class V3TodoServiceImpl implements TodoService{

	@Override
	public String getTodoList() {
		return "My TO-DO list from service. version - v3"; 
	}

}
