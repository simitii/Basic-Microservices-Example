package com.demo.microservices.oneservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.demo.microservices.oneservice.form.MessageForm;
import com.demo.microservices.oneservice.rabbitmq.QueueEnum;
import com.demo.microservices.oneservice.rabbitmq.Sender;

@Controller
public class OneServiceMainController {
	@Autowired
	private Sender sender;
	
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
		String messageToSent = messageForm.getMessage();
		sender.send(QueueEnum.TWO_SERVER, messageToSent);
		return "redirect:/";
	}

	public void setMessage(String s){
		message = s;
	}
}
