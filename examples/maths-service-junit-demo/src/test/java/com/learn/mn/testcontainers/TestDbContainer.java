package com.learn.mn.testcontainers;

import org.testcontainers.containers.MySQLContainer;

@SuppressWarnings("deprecation")
public class TestDbContainer extends MySQLContainer<TestDbContainer> {
	
	public void start() {
		super.start();
		System.setProperty("datasources.default.url", this.getJdbcUrl());
        System.setProperty("datasources.default.username", this.getUsername());
        System.setProperty("datasources.default.password", this.getPassword());
	}

}
