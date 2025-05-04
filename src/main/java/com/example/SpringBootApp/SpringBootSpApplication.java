package com.example.SpringBootApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication
@ComponentScan(basePackages = {"com.security",
		"com.example.customer",
		"com.example.blog",
		"com.example.controller",
		"com.example.utils"})
@EnableJpaRepositories(basePackages = {"com.example.customer","com.example.blog","com.example.utils"})
@EntityScan(basePackages = {"com.example.customer","com.example.blog","com.example.utils"})
public class SpringBootSpApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootSpApplication.class, args);
		System.out.println("終於阿~!!!!!!!!!!!~!!");
	}



}
