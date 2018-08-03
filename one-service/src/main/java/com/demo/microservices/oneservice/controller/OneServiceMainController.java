package com.demo.microservices.oneservice.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;

import com.netflix.discovery.EurekaClient;
import com.netflix.appinfo.InstanceInfo;

import com.demo.microservices.oneservice.form.MessageForm;

@Controller
public class OneServiceMainController {
	@Autowired
	private EurekaClient eurekaClient;
	
	private String message = "Message from One-Service";

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("message", message);
		MessageForm messageForm = new MessageForm();
		model.addAttribute("messageForm", messageForm);
		return "home";
	}

	@RequestMapping(value = { "/sendMessage" }, method = RequestMethod.POST)
	public String messageSender(Model model, @ModelAttribute("personForm") MessageForm messageForm){
		String serviceUrl = this.getServiceUrl("two-service");
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
		parts.add("message", messageForm.getMessage());
		restTemplate.postForEntity(serviceUrl + "/messageFromOtherService", parts, String.class);
		return "redirect:/";
	}

	@RequestMapping(value = { "/messageFromOtherService" }, method = RequestMethod.POST)
	public String messageProcessor(@RequestParam(value="message", defaultValue="New Message") 
		String message){
		this.message = message;
		return "redirect:/";
	}

	public String getServiceUrl(String serviceName) {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka(serviceName, false);
		return instance.getHomePageUrl();
	}
}
