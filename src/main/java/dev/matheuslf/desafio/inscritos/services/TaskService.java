package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.task.TaskFilterDTO;
import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

            newTask.setPriority(Task.Priority.valueOf(body.priority()));
            newTask.setStatus(Task.Status.valueOf(body.status()));

            return taskRepository.save(newTask);
        }
        throw new IllegalArgumentException("Projeto não encontrado");
    }

    public List<Task> filterTasks(TaskFilterDTO body) {
        if (body.status() != null && body.priority() != null && body.projectId() != null) {
            return taskRepository.findByStatusAndPriorityAndProject_Id(
                    body.status(),
                    body.priority(),
                    body.projectId()
            );
        }
        else if (body.status() != null && body.projectId() != null) {
            return taskRepository.findByStatusAndProject_Id(body.status(), body.projectId());
        }
        else if(body.priority() != null && body.projectId() != null) {
            return taskRepository.findByProject_IdAndPriority(body.projectId(), body.priority());
        }
        else if (body.status() != null && body.priority() != null) {
            return taskRepository.findByStatusAndPriority(body.status(), body.priority());
        }


        if (body.status() != null) {
            return taskRepository.findByStatus(body.status());
        }  if (body.priority() != null) {
            return taskRepository.findByPriority(body.priority());
        }  if (body.projectId() != null) {
            return taskRepository.findByProject_Id(body.projectId());
        }

        return taskRepository.findAll();
    }

    public Task updateStatus(Long taskId, Task.Status status) {
        var taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setStatus(status);
            return taskRepository.save(task);
        } else {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
    }

    public Task deleteById(Long taskId) {
        var taskOptional = taskRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            taskRepository.delete(task);
            return task;
        } else {
            throw new IllegalArgumentException("Tarefa não encontrada");
        }
    }

}

