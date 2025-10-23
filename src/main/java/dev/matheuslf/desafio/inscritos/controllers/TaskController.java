package dev.matheuslf.desafio.inscritos.controllers;

import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.task.TaskFilterDTO;
import dev.matheuslf.desafio.inscritos.dto.task.TaskFilterResponse;
import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.dto.task.TaskResponse;
import dev.matheuslf.desafio.inscritos.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public ResponseEntity<Task>
}
