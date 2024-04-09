package ru.vita.producer.configurations;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfiguration {
    public static final String QUEUE_NAME = "firstQueue";
    public static final String EXCHANGE_NAME = "firstExchange";
    public static final String ROUTING_KEY_1 = "routingKey.1";
    public static final String ROUTING_KEY_2 = "routingKey.2";

    @Bean
    public Queue myQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    Exchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, false, false);
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_1).noargs();
    }

}
