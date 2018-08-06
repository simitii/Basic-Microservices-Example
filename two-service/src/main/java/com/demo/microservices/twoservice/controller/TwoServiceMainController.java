package com.demo.microservices.twoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.demo.microservices.twoservice.form.MessageForm;
import com.demo.microservices.twoservice.rabbitmq.QueueEnum;
import com.demo.microservices.twoservice.rabbitmq.Sender;


@Controller
public class TwoServiceMainController {
	@Autowired
	private Sender sender;
	
	private String message = "Message from Two-Service";

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("message", message);
		MessageForm messageForm = new MessageForm();
		model.addAttribute("messageForm", messageForm);
		return "home";
	}

	@RequestMapping(value = { "/sendMessage" }, method = RequestMethod.POST)
	public String messageSender(Model model, @ModelAttribute("personForm") MessageForm messageForm){
		String messageToSent = messageForm.getMessage();
		sender.send(QueueEnum.ONE_SERVER, messageToSent);
		return "redirect:/";
	}

	public void setMessage(String s){
		message = s;
	}
}
