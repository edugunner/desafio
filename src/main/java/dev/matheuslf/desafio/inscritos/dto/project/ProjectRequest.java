package dev.matheuslf.desafio.inscritos.dto.project;

import dev.matheuslf.desafio.inscritos.domains.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ProjectRequest(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String name,
        String description,
        Date startDate,
        Date endDate

) {

    public Project toEntity() {
        Project project = new Project();
        project.setName(this.name);
        project.setDescription(this.description);
        project.setStartDate(this.startDate);
        project.setEndDate(this.endDate);
        return project;
    }
}
