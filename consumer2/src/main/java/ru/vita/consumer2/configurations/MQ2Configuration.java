package ru.vita.consumer2.configurations;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class MQ2Configuration {
    static final String QUEUE_NAME = "secondQueue";
    public static final String ROUTING_KEY_2 = "routingKey.2";
    static final String EXCHANGE_NAME = "firstExchange";

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
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_2).noargs();
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(String message) {
        log.info("Message read from second queue: {}", message);
    }

}
