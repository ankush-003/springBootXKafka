package com.ankush003.springBootXKafka.services;

public interface Consumer {
    void consumeTaskEvent(String message);

    org.apache.kafka.clients.consumer.Consumer<String, String> createConsumer();
}
