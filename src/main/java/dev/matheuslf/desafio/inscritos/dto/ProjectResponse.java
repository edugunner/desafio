// src/main/java/dev/matheuslf/desafio/inscritos/dto/ProjectResponse.java
package dev.matheuslf.desafio.inscritos.dto;

import dev.matheuslf.desafio.inscritos.domains.Project;
import java.util.Date;

public record ProjectResponse(
        Long id,
        String name,
        String description,
        Date startDate,
        Date endDate
) {
    public static ProjectResponse fromEntity(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate()
        );
    }
}