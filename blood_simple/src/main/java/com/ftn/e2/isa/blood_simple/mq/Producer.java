package com.ftn.e2.isa.blood_simple.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendTo(String queue, String message) {
        rabbitTemplate.convertAndSend("blood-simple-routing-key", message);
    }
    public void sendToExchange(String exchange, String queue, String message) {
        rabbitTemplate.convertAndSend("blood-simple-exchange", "blood-simple-routing-key", message);
    }

}
