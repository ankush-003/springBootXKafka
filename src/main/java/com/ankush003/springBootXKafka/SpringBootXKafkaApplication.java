package com.ankush003.springBootXKafka;

import com.ankush003.springBootXKafka.config.KafkaConfigProps;
import com.ankush003.springBootXKafka.domain.TaskEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class SpringBootXKafkaApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootXKafkaApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> kafkaTemplate,@Autowired final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
		final TaskEvent taskEvent = TaskEvent.builder()
				.userId(UUID.randomUUID().toString())
				.taskId(UUID.randomUUID().toString())
				.taskName("taskName1")
				.taskStatus("CREATED")
				.taskType("taskType1")
				.taskPayload("taskPayload1")
				.taskCreatedTime(LocalDateTime.now())
				.build();

		final String payload = objectMapper.writeValueAsString(taskEvent);
		System.out.println("payload = " + payload);

		return args -> {
			kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
		};
    }

	@KafkaListener(topics = "${ankush003.kafka.topic}")
	public String listen(String message) {
//		System.out.println("Received Messasge in group foo: " + message);
		log.info("Received Messasge in group foo: " + message);
		return message;
	}
}
