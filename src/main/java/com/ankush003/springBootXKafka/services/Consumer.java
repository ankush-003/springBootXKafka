package com.ankush003.springBootXKafka.services;

import com.ankush003.springBootXKafka.domain.TaskEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;

public interface Consumer {
    TaskEvent consumeTaskEvent(String message);

    void pollTaskEvent(Duration duration, SseEmitter emitter, org.apache.kafka.clients.consumer.Consumer<String, String> consumer);

    org.apache.kafka.clients.consumer.Consumer<String, String> createConsumer();
}
