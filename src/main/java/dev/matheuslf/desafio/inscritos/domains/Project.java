package dev.matheuslf.desafio.inscritos.domains;

import dev.matheuslf.desafio.inscritos.dto.project.ProjectRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    private String description;

    private Date startDate;
    private Date endDate;

    public Project(ProjectRequest body) {
        this.endDate = body.endDate();
        this.startDate = body.startDate();
        this.description = body.description();
        this.name = body.name();
    }
}
