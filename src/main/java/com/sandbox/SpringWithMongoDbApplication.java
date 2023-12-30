package com.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableMongoRepositories
@Slf4j
public class SpringWithMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithMongoDbApplication.class, args);
	}

}
