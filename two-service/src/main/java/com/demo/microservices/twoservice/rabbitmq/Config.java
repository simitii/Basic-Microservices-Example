package com.demo.microservices.twoservice.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class Config {
    @Bean
    public Queue toOneServer() {
        return new Queue("toOneServer");
    }

    @Bean
    public Queue toTwoServer() {
        return new Queue("toTwoServer");
    }

    @Bean
    public Queue toThreeServer() {
        return new Queue("toThreeServer");
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}