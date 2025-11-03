package dev.matheuslf.desafio.inscritos.dto.task;

import dev.matheuslf.desafio.inscritos.domains.Task;

import java.time.LocalDateTime;
import java.util.Date;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status,
        String priority,
        Date dueDate,
        Long projectId

) {

    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getPriority().name(),
                task.getDueDate(),
                task.getProject() != null ? task.getProject().getId() : null
        );
    }
}
