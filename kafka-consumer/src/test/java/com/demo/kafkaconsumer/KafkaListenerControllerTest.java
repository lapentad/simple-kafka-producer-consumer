package com.demo.kafkaconsumer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;

import com.demo.kafkaconsumer.controllers.KafkaListenerController;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@SpringBootTest
public class KafkaListenerControllerTest {

    @Mock
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Mock
    private MessageListenerContainer listenerContainer;

    @InjectMocks
    private KafkaListenerController kafkaListenerController;

    @Autowired
    private KafkaListenerController realKafkaListenerController;

    private String id = "testid";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(kafkaListenerEndpointRegistry.getListenerContainer(id)).thenReturn(listenerContainer);
    }

    @AfterEach
    public void tearDown() {
        reset(kafkaListenerEndpointRegistry, listenerContainer);
    }

    @Test
    public void testStartKafkaListener() {
        when(listenerContainer.isRunning()).thenReturn(true);

        Mono<String> result = realKafkaListenerController.startKafkaListener();

        StepVerifier.create(result)
                .expectNext("Kafka listener started")
                .verifyComplete();
    }

    @Test
    public void testStartKafkaListenerContainerNotFound() {
        when(kafkaListenerEndpointRegistry.getListenerContainer(id)).thenReturn(null);

        Mono<String> result = kafkaListenerController.startKafkaListener();

        StepVerifier.create(result)
                .expectNext("Kafka listener container not found")
                .verifyComplete();
    }

    @Test
    public void testStopKafkaListenerContainerNotFound() {
        when(kafkaListenerEndpointRegistry.getListenerContainer(id)).thenReturn(null);

        Mono<String> result = kafkaListenerController.stopKafkaListener();

        StepVerifier.create(result)
                .expectNext("Kafka listener container not found")
                .verifyComplete();
    }

}