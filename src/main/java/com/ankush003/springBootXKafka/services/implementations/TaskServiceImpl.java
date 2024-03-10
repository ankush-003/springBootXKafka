package com.ankush003.springBootXKafka.services.implementations;

import com.ankush003.springBootXKafka.domain.TaskEntity;
import com.ankush003.springBootXKafka.domain.TaskEvent;
import com.ankush003.springBootXKafka.repositories.TaskRepository;
import com.ankush003.springBootXKafka.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskEntity saveTask(TaskEvent taskEvent) {
        return taskRepository.save(convertToTaskEntity(taskEvent));
    }

    @Override
    public Optional<TaskEntity> getTaskById(String taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    @Override
    public List<TaskEntity> getTaskByUserId(String userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity convertToTaskEntity(TaskEvent taskEvent) {
        // random UUIDs for userId and taskId, current time for taskCreatedTime
        return TaskEntity.builder()
                .userId(UUID.randomUUID().toString())
                .taskId(UUID.randomUUID().toString())
                .taskName(taskEvent.getTaskName())
                .taskStatus(taskEvent.getTaskStatus())
                .taskType(taskEvent.getTaskType())
                .taskPayload(taskEvent.getTaskPayload())
                .taskCreatedTime(LocalDateTime.now())
                .build();
    }

    private TaskEvent convertToTaskEvent(TaskEntity taskEntity) {
        return TaskEvent.builder()
                .taskName(taskEntity.getTaskName())
                .taskStatus(taskEntity.getTaskStatus())
                .taskType(taskEntity.getTaskType())
                .taskPayload(taskEntity.getTaskPayload())
                .build();
    }
}
