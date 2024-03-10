package com.ankush003.springBootXKafka.controllers;

import com.ankush003.springBootXKafka.domain.TaskEvent;
import com.ankush003.springBootXKafka.services.Consumer;
import com.ankush003.springBootXKafka.services.Producer;
import com.ankush003.springBootXKafka.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TaskController {

    @Autowired
    private Producer producer;

    @Autowired
    private Consumer consumer;

    @Autowired
    private TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTaskById(String taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/consume")
    public ResponseEntity<?> consumeTaskEvent(String message) {
        return ResponseEntity.ok(consumer.consumeTaskEvent(message));
    }

    @GetMapping("/stream/tasks")
    public SseEmitter streamTasks() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                consumer.pollTaskEvent(Duration.ofSeconds(1), emitter);
//                Thread.sleep(1000);
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @PostMapping("/produce")
    public ResponseEntity<TaskEvent> produceTaskEvent(@RequestBody TaskEvent taskEvent) {
        producer.sendTaskEvent(taskEvent);
        taskService.saveTask(taskEvent);
        return ResponseEntity.ok(taskEvent);
    }
}
