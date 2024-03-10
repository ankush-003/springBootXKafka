package com.ankush003.springBootXKafka.services.implementations;

import com.ankush003.springBootXKafka.config.KafkaConfigProps;
import com.ankush003.springBootXKafka.domain.TaskEntity;
import com.ankush003.springBootXKafka.services.Producer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class TaskProducer implements Producer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void produceTaskEntity(TaskEntity taskEntity) {
        try {
            final String payload = objectMapper.writeValueAsString(taskEntity);
            log.info("Sending payload: " + payload);
            kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
        } catch (Exception e) {
            log.error("Error while sending message to Kafka: " + e.getMessage());
        }
    }
}
