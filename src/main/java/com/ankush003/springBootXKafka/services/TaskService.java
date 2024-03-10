package com.ankush003.springBootXKafka.services;

import com.ankush003.springBootXKafka.domain.TaskEntity;
import com.ankush003.springBootXKafka.domain.TaskEvent;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    TaskEntity saveTask(TaskEvent taskEvent);

    List<TaskEntity> getAllTasks();

    Optional<TaskEntity> getTaskById(String taskId);

    List<TaskEntity> getTaskByUserId(String userId);

    TaskEntity convertToTaskEntity(TaskEvent taskEvent);
}
