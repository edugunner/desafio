package dev.matheuslf.desafio.inscritos.dto.task;

import dev.matheuslf.desafio.inscritos.domains.Task;

public record TaskFilterResponse (
        Task.Status status,
        Task.Priority priority,
        Long projectId)
    {
        public static TaskFilterResponse fromEntity(Task task) {
            return new TaskFilterResponse(
                    task.getStatus(),
                    task.getPriority(),
                    task.getProject().getId()
            );
        }
}
