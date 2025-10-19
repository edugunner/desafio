package dev.matheuslf.desafio.inscritos.dto.task;

import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.domains.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record TaskRequest(
        @NotBlank(message = "O título não pode estar em branco")
        @Size(min = 5, max = 150, message = "O nome deve ter entre 5 e 150 caracteres")
        String title,
        String description,
        String status,
        String priority,
        Date dueDate,

        @NotNull(message = "O ID do projeto (projectId) é obrigatório")
        Long projectId
        ) {

        public Task toEntity() {
            Task task = new Task();
            task.setTitle(title);
            task.setDescription(this.description);
            task.setStatus(Task.Status.valueOf(this.status));
            task.setPriority(Task.Priority.valueOf(this.priority));
            task.setDueDate(this.dueDate);
            return task;
        }
}
