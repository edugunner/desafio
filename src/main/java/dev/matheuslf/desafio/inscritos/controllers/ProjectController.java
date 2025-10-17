package dev.matheuslf.desafio.inscritos.controllers;


import dev.matheuslf.desafio.inscritos.domains.Project;
import dev.matheuslf.desafio.inscritos.dto.ProjectRequest;
import dev.matheuslf.desafio.inscritos.dto.ProjectResponse;
import dev.matheuslf.desafio.inscritos.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
