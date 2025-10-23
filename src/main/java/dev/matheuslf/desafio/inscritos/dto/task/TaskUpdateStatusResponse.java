package dev.matheuslf.desafio.inscritos.dto.task;

import dev.matheuslf.desafio.inscritos.domains.Task;

public record TaskUpdateStatusResponse(
        Long id,
        Task.Status status
) {
    public static TaskUpdateStatusResponse fromEntity(Task task) {
        return new TaskUpdateStatusResponse(
                task.getId(),
                task.getStatus()
        );
    }
}
