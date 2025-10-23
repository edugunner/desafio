package dev.matheuslf.desafio.inscritos.dto.task;

import dev.matheuslf.desafio.inscritos.domains.Task;

public record TaskUpdateStatusRequest(
        Long id,
        Task.Status status
) {
    public Task toEntity() {
        Task task = new Task();
        task.setId(this.id);
        task.setStatus(this.status);
        return task;
    }
}
