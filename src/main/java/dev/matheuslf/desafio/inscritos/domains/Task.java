package dev.matheuslf.desafio.inscritos.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Task {


    @GeneratedValue(generator = "Long")
    @Id
    private Long id;

    @NotNull
    private String title;
    private String description;


    private enum Status {
        TODO,
        DOING,
        DONE
    }

    private enum priority {
        LOW,
        MEDIUM,
        HIGH
    }

    private Date dueDate;
    private Project projectId;

}
