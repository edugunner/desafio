package dev.matheuslf.desafio.inscritos.repositories;

import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.dto.task.TaskFilterDTO;
import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ActiveProfiles("test")
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EntityManager entityManager;



    @Test
    @DisplayName("Quando o filtro encontra o Status")
    void findByStatusCase1() {
        var project = this.createProject();
        Task.Status expectedStatus = Task.Status.DONE;

        var task = createTask(expectedStatus, Task.Priority.MEDIUM, project);

        List<Task> result = taskRepository.findByStatus(expectedStatus);


        assertThat(result).isNotNull();
        assertThat(result.get(0).getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    @DisplayName("Quando o filtro não encontrar o Status")
    void findByStatusCase2() {

        var project = this.createProject();
        this.createTask(Task.Status.DONE, Task.Priority.MEDIUM, project);


        List<Task> result = taskRepository.findByStatus(Task.Status.TODO);


        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Filtro para projectId")
    void findByProject_IdCase1() {
        var project = this.createProject();

        var task = createTask(Task.Status.DONE, Task.Priority.MEDIUM, project);

        List<Task> result = taskRepository.findByProject_Id(project.getId());

        assertThat(result).isNotNull();
        assertThat(result.get(0).getProject().getId()).isEqualTo(project.getId());
    }

    @Test
    @DisplayName("Filtro para projectId nao encontra")
    void findByProject_IdCase2() {
        var project = this.createProject();
        var task = createTask(Task.Status.DONE, Task.Priority.MEDIUM, project);

        List<Task> result = taskRepository.findByProject_Id(2L);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Filtro para priority")
    void findByPriorityCase1() {
        var project = this.createProject();

        var task = createTask(Task.Status.DONE, Task.Priority.MEDIUM, project);

        List<Task> result = taskRepository.findByPriority(task.getPriority());

        assertThat(result).isNotNull();
        assertThat(result.get(0).getPriority()).isEqualTo(task.getPriority());
    }

    @Test
    @DisplayName("Filtro para priority não encontra")
    void findByPriorityCase2() {
        var project = this.createProject();
        var task = createTask(Task.Status.DONE, Task.Priority.MEDIUM, project);

        List<Task> result = taskRepository.findByPriority(Task.Priority.LOW);

        assertThat(result).isNotNull();
    }



    private Project createProject() {
        Project project = new Project();
        project.setName("projeto teste");
        this.entityManager.persist(project);
        this.entityManager.flush();
        return project;
    }

    private Task createTask(Task.Status status,Task.Priority priority,  Project project) {
        Task task = new Task();
        task.setStatus(status);
        task.setPriority(priority);
        task.setProject(project);
        task.setDueDate(new Date(2026-25-10));
        task.setTitle("titulo teste");
        this.entityManager.persist(task);
        this.entityManager.flush();

        return task;
    }
}