package ru.vita.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class ConsumerApplication {
	static final String QUEUE_NAME = "firstQueue";

	@Bean
	public Queue myQueue() {
		return new Queue(QUEUE_NAME, false);
	}

	@RabbitListener(queues = QUEUE_NAME)
	public void listen(String message) {
		log.info("Message read from first queue: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
