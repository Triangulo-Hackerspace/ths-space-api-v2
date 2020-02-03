package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Project;
import br.net.triangulohackerspace.thsspaceapi.service.ProjectService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/projects")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectController.class);

	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@PostMapping
	public Project createProject(@RequestBody @Valid final Project project) {
		LOGGER.debug("Received request to create the {}", project);
		return projectService.save(project);
	}

	@GetMapping
	public List<Project> listProjects() {
		LOGGER.debug("Received request to list all projects");
		return projectService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleProjectAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
