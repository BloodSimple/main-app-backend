package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.mq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producer")
public class PruducerController {

    @Autowired
    private Producer producer;

    @PostMapping(value="/{queue}", consumes = "text/plain")
    public ResponseEntity<String> sendMessage(@PathVariable("queue") String queue, @RequestBody String message) {
        producer.sendTo(queue, message);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/{exchange}/{queue}", consumes = "text/plain")
    public ResponseEntity<String> sendMessageToExchange(@PathVariable("exchange") String exchange, @PathVariable("queue") String queue, @RequestBody String message) {
        producer.sendToExchange(exchange, queue, message);
        return ResponseEntity.ok().build();
    }
}
