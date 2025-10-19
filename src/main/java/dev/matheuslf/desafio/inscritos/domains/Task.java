package dev.matheuslf.desafio.inscritos.domains;

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
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private String title;
    private String description;


    public enum Status {
        TODO,
        DOING,
        DONE
    }
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
