package com.ankush003.springBootXKafka.services.implementations;

import com.ankush003.springBootXKafka.config.KafkaConfigProps;
import com.ankush003.springBootXKafka.domain.TaskEvent;
import com.ankush003.springBootXKafka.services.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class TaskConsumer implements Consumer {

    private ConsumerFactory<String, String> consumerFactory;

    private KafkaConfigProps kafkaConfigProps;

    private ObjectMapper objectMapper;

    private org.apache.kafka.clients.consumer.Consumer<String, String> consumer;

    TaskConsumer(@Autowired ConsumerFactory<String, String> consumerFactory, @Autowired KafkaConfigProps kafkaConfigProps, @Autowired ObjectMapper objectMapper) {
        this.consumerFactory = consumerFactory;
        this.kafkaConfigProps = kafkaConfigProps;
        this.objectMapper = objectMapper;
        consumer = consumerFactory.createConsumer();
        consumer.subscribe(List.of(kafkaConfigProps.getTopic()));
    }

    @Override
    @KafkaListener(topics = "${ankush003.kafka.topic}")
    public TaskEvent consumeTaskEvent(String message) {
        log.info("Received Messasge in group foo: " + message);
        return objectMapper.convertValue(message, TaskEvent.class);
    }

    @Override
    public void pollTaskEvent(Duration duration, SseEmitter emitter) {
        ConsumerRecords<String, String> records = consumer.poll(duration);
        for (ConsumerRecord<String, String> record : records) {
            log.info("Received message: " + record.value());
            SseEmitter.SseEventBuilder event = SseEmitter.event().
                    id(String.valueOf(record.offset())).
                    name("taskEvent").
                    data(record.value());
            try {
                emitter.send(event);
            } catch (Exception e) {
                log.error("Error while sending message to client: " + e.getMessage());
            }
        }
    }
}
