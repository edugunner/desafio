package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest body) {
        var taskCreated = taskService.createTask(body);

        TaskResponse taskResponse = TaskResponse.fromEntity(taskCreated);

        return ResponseEntity.status(201).body(taskResponse);
    }
}
