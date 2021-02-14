package com.springbootkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringbootkafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootkafkaApplication.class, args);
		System.out.println("Application Started!");
		System.out.println("Date in 'UTC': " + new Date().toString());

	}
}
