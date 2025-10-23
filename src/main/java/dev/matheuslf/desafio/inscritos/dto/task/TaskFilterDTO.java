package dev.matheuslf.desafio.inscritos.dto.task;

import dev.matheuslf.desafio.inscritos.domains.Task;

public record TaskFilterDTO(
        Task.Status status,
        Task.Priority priority,
        Long projectId

) {
}
