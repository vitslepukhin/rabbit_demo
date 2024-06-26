package ru.vita.producer.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static ru.vita.producer.configurations.MQConfiguration.EXCHANGE_NAME;
import static ru.vita.producer.configurations.MQConfiguration.ROUTING_KEY_1;
import static ru.vita.producer.configurations.MQConfiguration.ROUTING_KEY_2;

@Log4j2
@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class SendController {
    private final RabbitTemplate rabbitTemplate;
    private static Long counter = 0L;

    @GetMapping("/qe1")
    public ResponseEntity<String> sendMessageToQ1(@RequestParam String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_1, message);
        log(message);

        return ResponseEntity.ok("Sent to Q1!");
    }

    @GetMapping("/qe2")
    public ResponseEntity<String> sendMessageToQ2(@RequestParam String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_2, message);
        log(message);

        return ResponseEntity.ok("Sent to Q2!");
    }

    private static void log(String message) {
        log.info("{} is send! Count sent object :{}", message, ++counter);
    }
}
