package dev.matheuslf.desafio.inscritos.services;

import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }




    public Project createProject(ProjectRequest body) {

        Project newProject = new Project();

        newProject.setName(body.name());
        newProject.setDescription(body.description());
        newProject.setStartDate(body.startDate());
        newProject.setEndDate(body.endDate());

        return projectRepository.save(newProject);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
