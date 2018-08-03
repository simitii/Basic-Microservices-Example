package com.demo.microservices.apigateaway.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiGatewayMainController {
	
	@GetMapping(value = "/")
	public String home() {
		return "API-Gateway Home Page";
	}
}
