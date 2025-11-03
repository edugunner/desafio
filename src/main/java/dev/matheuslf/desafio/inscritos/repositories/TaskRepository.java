package dev.matheuslf.desafio.inscritos.repositories;

import dev.matheuslf.desafio.inscritos.domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task>{
    List<Task> findByStatus(Task.Status status);

    List<Task> findByProject_Id(Long projectId);

    List<Task> findByPriority(Task.Priority priority);

    List<Task> findByStatusAndProject_Id(Task.Status status, Long projectId);

    List<Task> findByStatusAndPriority(Task.Status status, Task.Priority priority);

    List<Task> findByProject_IdAndPriority(Long projectId, Task.Priority priority);

    List<Task> findByStatusAndPriorityAndProject_Id(Task.Status status, Task.Priority priority, Long projectId);
}
