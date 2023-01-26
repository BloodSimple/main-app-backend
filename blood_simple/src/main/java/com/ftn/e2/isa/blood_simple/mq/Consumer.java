package com.ftn.e2.isa.blood_simple.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues= "${myapp.rabbitmq.queue1}")
    public void handler(String message){
        System.out.println("Message received: " + message);
    }

}
