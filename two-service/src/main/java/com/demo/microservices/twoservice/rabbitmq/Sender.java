package com.demo.microservices.twoservice.rabbitmq;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class Sender {
    @Autowired
    private RabbitTemplate template;

    public void send(QueueEnum queueEnum, String message) {
        JSONObject json = new JSONObject();
        json.put("message", message);
        this.template.convertAndSend(queueEnum.getName(), json.toString());
        System.out.println(" [x] Sent '" + message + "'");
    }
}