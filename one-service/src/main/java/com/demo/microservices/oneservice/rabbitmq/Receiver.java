package com.demo.microservices.oneservice.rabbitmq;

import com.demo.microservices.oneservice.controller.OneServiceMainController;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "toOneServer")
public class Receiver {

    @Autowired
    OneServiceMainController controller;

    @RabbitHandler
    public void messageProcessor(Object in) {
        String s = new String(((Message)in).getBody());
        try{
            JSONObject json = new JSONObject(s);
            System.out.println(" [x] Received '" + json + "'");
            controller.setMessage((String)json.get("message"));
        } catch (JSONException ex) {
            System.out.println("ERROR: Unknown message type");
        }
    }
}