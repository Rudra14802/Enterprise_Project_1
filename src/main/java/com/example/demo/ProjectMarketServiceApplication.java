package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableReactiveMongoRepositories
public class ProjectMarketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMarketServiceApplication.class, args);
	}

}
