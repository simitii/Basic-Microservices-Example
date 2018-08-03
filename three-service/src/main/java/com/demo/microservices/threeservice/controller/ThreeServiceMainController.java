package com.demo.microservices.threeservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ThreeServiceMainController {
	
	@GetMapping(value = "/")
	public String home() {
		return "Three-Service Home Page!";
	}
}
