package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {


    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task createTask(TaskRequest body) {
        var projectId = projectRepository.findById(body.projectId());

        if (projectId.isPresent()) {
            Task newTask = new Task();

            newTask.setTitle(body.title());
            newTask.setDescription(body.description());

            newTask.setProject(projectId.get());
            newTask.setDueDate(body.dueDate());

            if (newTask.getStatus() == null) {
                newTask.setStatus(Task.Status.TODO);
            } else {
                newTask.setStatus(Task.Status.valueOf(body.status()));
            }

            if (newTask.getPriority() == null) {
                newTask.setPriority(Task.Priority.LOW);
            } else {
                newTask.setPriority(Task.Priority.valueOf(body.priority()));
            }

            return taskRepository.save(newTask);
        }
        throw new IllegalArgumentException("Projeto não encontrado");
    }
}
