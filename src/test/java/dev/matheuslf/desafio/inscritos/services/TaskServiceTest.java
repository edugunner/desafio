package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.domains.Task;
import dev.matheuslf.desafio.inscritos.dto.task.TaskFilterDTO;
import dev.matheuslf.desafio.inscritos.dto.task.TaskRequest;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    private Task mockTask;
    private Project mockProject;
    private TaskRequest mockRequest;
    private final Long MOCK_TASK_ID = 1L;
    private final Long MOCK_PROJECT_ID = 10L;

    @BeforeEach
    void setUp() {
        mockProject = new Project();
        mockProject.setId(MOCK_PROJECT_ID);

        mockTask = new Task();
        mockTask.setId(MOCK_TASK_ID);
        mockTask.setTitle("Tarefa Mock");
        mockTask.setProject(mockProject);
        mockTask.setStatus(Task.Status.TODO);
        mockTask.setPriority(Task.Priority.LOW);

        mockRequest = new TaskRequest(
                "Nova Tarefa", "Desc", "TODO", "LOW",
                new Date(2025-11-10), MOCK_PROJECT_ID
        );
    }

    @Test
    @DisplayName("Deve criar e salvar a Task se o Projeto for encontrado")
    void createTaskCase1() {
        when(projectRepository.findById(MOCK_PROJECT_ID))
                .thenReturn(Optional.of(mockProject));

        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);


        Task createdTask = taskService.createTask(mockRequest);

        assertNotNull(createdTask);
        assertEquals(MOCK_TASK_ID, createdTask.getId());
        assertEquals("Tarefa Mock", createdTask.getTitle());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException se o Projeto não for encontrado")
    void createTaskCase2() {
        when(projectRepository.findById(MOCK_PROJECT_ID))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            taskService.createTask(mockRequest);
        });

        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve chamar findByStatusAndPriorityAndProject_Id quando todos os filtros estiverem presentes")
    void filterTasksCase1() {
        TaskFilterDTO filterDTO = new TaskFilterDTO(
                Task.Status.DONE, Task.Priority.HIGH, MOCK_PROJECT_ID
        );
        when(taskRepository.findByStatusAndPriorityAndProject_Id(any(), any(), any()))
                .thenReturn(Collections.singletonList(mockTask));

        List<Task> result = taskService.filterTasks(filterDTO);

        assertFalse(result.isEmpty());

        verify(taskRepository, times(1)).findByStatusAndPriorityAndProject_Id(any(), any(), any());

        verify(taskRepository, never()).findByStatus(any());
        verify(taskRepository, never()).findByProject_Id(any());
    }

    @Test
    @DisplayName("Deve chamar findByStatus se apenas o status estiver presente")
    void filterTasksCase2() {
        TaskFilterDTO filterDTO = new TaskFilterDTO(Task.Status.DONE, null, null);
        when(taskRepository.findByStatus(any())).thenReturn(Collections.singletonList(mockTask));

        taskService.filterTasks(filterDTO);

        verify(taskRepository, times(1)).findByStatus(Task.Status.DONE);
    }

    @Test
    @DisplayName("Deve lançar NoSuchElementException se nenhum filtro for fornecido")
    void filterTasksCase3() {
        TaskFilterDTO filterDTO = new TaskFilterDTO(null, null, null);

        assertThrows(NoSuchElementException.class, () -> {
            taskService.filterTasks(filterDTO);
        });

        verifyNoInteractions(taskRepository);
    }

    @Test
    @DisplayName("Deve atualizar e salvar o status da Task quando a Task for encontrada")
    void updateStatusCase1() {
        when(taskRepository.findById(MOCK_TASK_ID)).thenReturn(Optional.of(mockTask));

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task task = invocation.getArgument(0);
            assertEquals(Task.Status.DONE, task.getStatus());
            return task;
        });

        Task updatedTask = taskService.updateStatus(MOCK_TASK_ID, Task.Status.DONE);

        assertEquals(Task.Status.DONE, updatedTask.getStatus());
        verify(taskRepository, times(1)).save(mockTask);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao tentar atualizar status de Task inexistente")
    void updateStatusCase2() {
        when(taskRepository.findById(MOCK_TASK_ID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateStatus(MOCK_TASK_ID, Task.Status.DONE);
        });

        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve deletar a Task e retornar a Task quando encontrada")
    void deleteByIdCase1() {
        when(taskRepository.findById(MOCK_TASK_ID)).thenReturn(Optional.of(mockTask));

        doNothing().when(taskRepository).delete(mockTask);

        Task deletedTask = taskService.deleteById(MOCK_TASK_ID);

        assertNotNull(deletedTask);
        assertEquals(MOCK_TASK_ID, deletedTask.getId());

        verify(taskRepository, times(1)).delete(mockTask);
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao tentar deletar Task inexistente")
    void deleteByIdCase2() {
        when(taskRepository.findById(MOCK_TASK_ID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteById(MOCK_TASK_ID);
        });

        verify(taskRepository, never()).delete(any(Task.class));
    }
}

