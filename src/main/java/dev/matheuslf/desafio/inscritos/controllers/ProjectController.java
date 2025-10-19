package dev.matheuslf.desafio.inscritos.controllers;


import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.dto.project.ProjectRequest;
import dev.matheuslf.desafio.inscritos.dto.project.ProjectResponse;
import dev.matheuslf.desafio.inscritos.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping()
    public ResponseEntity<ProjectResponse> newProject(@RequestBody @Valid ProjectRequest body) {
        var projectCreated = projectService.createProject(body);

        ProjectResponse projectResponse = ProjectResponse.fromEntity(projectCreated);

        return ResponseEntity.status(201).body(projectResponse);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ProjectResponse> responseList = projects.stream()
                .map(ProjectResponse::fromEntity)
                .toList();
        return ResponseEntity.ok().body(responseList);
    }
}
