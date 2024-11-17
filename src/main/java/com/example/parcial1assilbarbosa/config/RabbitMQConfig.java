package com.example.parcial1assilbarbosa.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue productionQueue() {
        return new Queue("production.queue", true);
    }

    @Bean
    public Queue assemblyQueue() {
        return new Queue("assembly.queue", true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("factory.exchange");
    }

    @Bean
    public Binding productionBinding(Queue productionQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productionQueue).to(exchange).with("production.#");
    }

    @Bean
    public Binding assemblyBinding(Queue assemblyQueue, TopicExchange exchange) {
        return BindingBuilder.bind(assemblyQueue).to(exchange).with("assembly.#");
    }
}

