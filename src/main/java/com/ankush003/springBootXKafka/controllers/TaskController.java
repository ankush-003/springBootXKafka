package com.ankush003.springBootXKafka.controllers;

import com.ankush003.springBootXKafka.domain.TaskEntity;
import com.ankush003.springBootXKafka.domain.TaskEvent;
import com.ankush003.springBootXKafka.services.Consumer;
import com.ankush003.springBootXKafka.services.Producer;
import com.ankush003.springBootXKafka.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private Sinks.Many<TaskEntity> sink;

    @Autowired
    private Flux<TaskEntity> taskBroadcast;

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Autowired
    private TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable String taskId) {
        Optional<TaskEntity> taskEntity = taskService.getTaskById(taskId);
        return taskEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskEntity>> getTaskByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(taskService.getTaskByUserId(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping(value="/stream", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TaskEntity> streamTaskEvents() {
        return taskBroadcast;
    }

    @PostMapping("/produce")
    public ResponseEntity<TaskEntity> produceTaskEvent(@RequestBody TaskEvent taskEvent) {
        TaskEntity savedTaskEntity = taskService.saveTask(taskEvent);
        producer.produceTaskEntity(savedTaskEntity);
        // sink.tryEmitNext(savedTaskEntity);
        return ResponseEntity.ok(savedTaskEntity);
    }
}
