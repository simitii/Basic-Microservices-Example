package com.demo.microservices.twoservice.rabbitmq;

public enum QueueEnum {
    ONE_SERVER("toOneServer"),
    TWO_SERVER("toTwoServer");
    private final String name;

    QueueEnum(final String text) {
        name = text;
    }

    String getName(){
        return name;
    }
}