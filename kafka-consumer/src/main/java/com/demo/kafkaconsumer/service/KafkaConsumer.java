package com.demo.kafkaconsumer.service;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(id = "${spring.kafka.id}", topics = "${spring.kafka.topic}", autoStartup = "${spring.kafka.autostart}")
    public Mono<String> listen(@Payload String data) {
        logger.info(data);
        return Mono.just(data);
    }
}
