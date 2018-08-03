package com.demo.microservices.twoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class TwoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoServiceApplication.class, args);
	}
}
