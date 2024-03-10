package com.ankush003.springBootXKafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

// sample json
// {
//     "userId": "user1",
//     "taskId": "task1",
//     "taskName": "task1",
//     "taskStatus": "PENDING",
//     "taskType": "EMAIL",
//     "taskPayload": "email payload",
// }
// {
//     "userId": "user2",
//     "taskId": "task2",
//     "taskName": "task2",
//     "taskStatus": "PENDING",
//     "taskType": "SMS",
//     "taskPayload": "sms payload",
// }
