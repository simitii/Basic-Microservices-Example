package com.demo.microservices.oneservice.rabbitmq;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(QueueEnum queueEnum, String message) {
        JSONObject json = new JSONObject();
        json.put("message", message);
        this.template.convertAndSend(queueEnum.getName(), json.toString());
        System.out.println(" [x] Sent '" + message + "'");
    }
}