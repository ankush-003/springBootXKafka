package com.ankush003.springBootXKafka.config;

import com.ankush003.springBootXKafka.domain.TaskEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {
    @Bean
    public Sinks.Many<TaskEntity> TaskSink() {
        return Sinks.many().replay().limit(1);
    }

    @Bean
    public Flux<TaskEntity> TaskBroadcast(Sinks.Many<TaskEntity> sink) {
        return sink.asFlux();
    }
}
