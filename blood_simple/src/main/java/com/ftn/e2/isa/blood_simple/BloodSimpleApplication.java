package com.ftn.e2.isa.blood_simple;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("repository")
@ComponentScan("service")
@ComponentScan("controller")
public class BloodSimpleApplication implements CommandLineRunner {

    @Value("${myapp.rabbitmq.queue2}")
    String queue1;
    @Value("${myapp.rabbitmq.queue1}")
    String queue2;
    @Value("${myapp.rabbitmq.exchange}")
    String exchange;
    @Value("${myapp.rabbitmq.routingKey}")
    String routingKey;

    public static void main(String[] args) {
        SpringApplication.run(BloodSimpleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // TODO Auto-generated method stub

    }

    @Bean
    Queue queue1() {
        return new Queue(queue1, true);
    }

    @Bean
    Queue queue2() {
        return new Queue(queue2, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue1, DirectExchange exchange) {
        return BindingBuilder.bind(this.queue2()).to(exchange).with(routingKey);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(8080);
        return connectionFactory;
    }


}
