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
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void saveTask(TaskEvent taskEvent) {
        taskRepository.save(convertToTaskEntity(taskEvent));
    }

    @Override
    public Optional<TaskEvent> getTaskById(String taskId) {
        TaskEntity taskEntity = taskRepository.findByTaskId(taskId);
        if (taskEntity != null) {
            return Optional.of(convertToTaskEvent(taskEntity));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TaskEvent> getTaskByUserId(String userId) {
        TaskEntity taskEntity = taskRepository.findByUserId(userId);
        if (taskEntity != null) {
            return Optional.of(convertToTaskEvent(taskEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<TaskEvent> getAllTasks() {
        List<TaskEntity> taskEntities = taskRepository.findAll();
        return taskEntities.stream().map(taskEntity -> convertToTaskEvent(taskEntity)).collect(Collectors.toList());
    }

    private TaskEvent convertToTaskEvent(TaskEntity taskEntity) {
        return TaskEvent.builder()
                .taskId(taskEntity.getTaskId())
                .userId(taskEntity.getUserId())
                .taskName(taskEntity.getTaskName())
                .taskStatus(taskEntity.getTaskStatus())
                .taskType(taskEntity.getTaskType())
                .taskPayload(taskEntity.getTaskPayload())
                .build();
    }

    private TaskEntity convertToTaskEntity(TaskEvent taskEvent) {
        return TaskEntity.builder()
                .taskId(taskEvent.getTaskId())
                .userId(taskEvent.getUserId())
                .taskName(taskEvent.getTaskName())
                .taskStatus(taskEvent.getTaskStatus())
                .taskType(taskEvent.getTaskType())
                .taskPayload(taskEvent.getTaskPayload())
                .taskCreatedTime(LocalDateTime.now())
                .build();
    }
}
