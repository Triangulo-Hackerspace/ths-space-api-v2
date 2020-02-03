package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Project;
import br.net.triangulohackerspace.thsspaceapi.repository.ProjectRepository;
import br.net.triangulohackerspace.thsspaceapi.service.ProjectService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(final ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Project save(@NotNull @Valid final Project project) {
        LOGGER.debug("Creating {}", project);
        Optional<Project> existing = repository.findById(project.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a project with id=%s", project.getId()));
        }
        return repository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> getList() {
		LOGGER.debug("Retrieving the list of all projects");
        return repository.findAll();
    }

}
