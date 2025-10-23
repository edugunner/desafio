package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.task.*;
import dev.matheuslf.desafio.inscritos.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest body) {
        var taskCreated = taskService.createTask(body);

        TaskResponse taskResponse = TaskResponse.fromEntity(taskCreated);

        return ResponseEntity.status(201).body(taskResponse);
    }

    @GetMapping
    public ResponseEntity<List<TaskFilterResponse>> filterAllTasks(@ModelAttribute TaskFilterDTO body) {
        var filterTasks = taskService.filterTasks(body);

        List<TaskFilterResponse> responseList = filterTasks.stream()
                .map(TaskFilterResponse::fromEntity)
                .toList();
        return ResponseEntity.status(200).body(responseList);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long id, @RequestBody @Valid TaskUpdateStatusRequest body) {
        var updatedTask = taskService.updateStatus(id, body.status());

        TaskResponse response = TaskResponse.fromEntity(updatedTask);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
