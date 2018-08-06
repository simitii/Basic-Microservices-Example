package com.demo.microservices.twoservice.rabbitmq;

import com.demo.microservices.twoservice.controller.TwoServiceMainController;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "toTwoServer")
public class Receiver {

    @Autowired
    TwoServiceMainController controller;

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