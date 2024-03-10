package com.ankush003.springBootXKafka.services;

import com.ankush003.springBootXKafka.domain.TaskEvent;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void saveTask(TaskEvent taskEvent);

    List<TaskEvent> getAllTasks();

    Optional<TaskEvent> getTaskById(String taskId);

    Optional<TaskEvent> getTaskByUserId(String userId);
}
