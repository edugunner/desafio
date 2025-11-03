package dev.matheuslf.desafio.inscritos.domains;

import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    public Task(TaskRequest body) {
        this.title = body.title();
        this.description = body.description();
        Status status1 = Status.valueOf(body.status());
        Priority priority1 = Priority.valueOf(body.priority());
        this.dueDate = body.dueDate();

    }

}
