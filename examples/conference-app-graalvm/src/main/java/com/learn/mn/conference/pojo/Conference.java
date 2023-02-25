package com.learn.mn.conference.pojo;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Conference {
	
	private String name;
	
	public Conference(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
