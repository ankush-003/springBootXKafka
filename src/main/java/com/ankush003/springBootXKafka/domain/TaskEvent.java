package com.ankush003.springBootXKafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEvent {
    private String userId;
    private String taskId;
    private String taskName;
    private String taskStatus;
    private String taskType;
    private String taskPayload;
    private LocalDateTime taskCreatedTime;
}
