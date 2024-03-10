package com.ankush003.springBootXKafka.services.implementations;

import com.ankush003.springBootXKafka.config.KafkaConfigProps;
import com.ankush003.springBootXKafka.domain.TaskEntity;
import com.ankush003.springBootXKafka.services.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
@Slf4j
public class TaskConsumer implements Consumer {

    @Autowired
    private ConsumerFactory<String, String> consumerFactory;

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Sinks.Many<TaskEntity> sink;

    private org.apache.kafka.clients.consumer.Consumer<String, String>  consumer;

    @Override
    @KafkaListener(topics = "${ankush003.kafka.topic}")
    public void consumeTaskEvent(String message) {
        log.info("Received Messasge in group foo: " + message);
        try {
            TaskEntity taskEntity = objectMapper.readValue(message, TaskEntity.class);
            sink.tryEmitNext(taskEntity);
        } catch (Exception e) {
            log.error("Error while consuming message: " + e.getMessage());
        }
    }

    @Override
    public org.apache.kafka.clients.consumer.Consumer<String, String> createConsumer() {
        consumer = consumerFactory.createConsumer();
        consumer.subscribe(List.of(kafkaConfigProps.getTopic()));
        return consumer;
    }
}
