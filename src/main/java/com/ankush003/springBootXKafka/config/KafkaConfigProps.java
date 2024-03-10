package com.ankush003.springBootXKafka.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ankush003.kafka")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaConfigProps {
//    @Bean
//    public NewTopic taskEvents() {
//        return TopicBuilder.name("task-events")
//                .partitions(3)
//                .replicas(1)
//                .build();
//    }
    private String topic;
}
