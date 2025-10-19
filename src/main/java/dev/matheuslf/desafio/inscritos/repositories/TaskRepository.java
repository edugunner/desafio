package dev.matheuslf.desafio.inscritos.repositories;

import dev.matheuslf.desafio.inscritos.domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
