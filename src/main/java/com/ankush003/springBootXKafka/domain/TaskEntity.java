package com.ankush003.springBootXKafka.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "tasks")
public class TaskEntity {
    @Id
    private String taskId;
    private String userId;
    private String taskName;
    private String taskStatus;
    private String taskType;
    private String taskPayload;
    private LocalDateTime taskCreatedTime;
}
