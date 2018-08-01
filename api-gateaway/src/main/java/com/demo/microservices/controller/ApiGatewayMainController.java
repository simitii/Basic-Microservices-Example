package com.demo.microservices.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiGatewayMainController {
	
	@GetMapping (value = "/")
	public String home() {
		return "Message from API-Gateway!!";
	}
}
