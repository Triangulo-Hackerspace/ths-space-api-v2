package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Spacefed;
import br.net.triangulohackerspace.thsspaceapi.repository.SpacefedRepository;
import br.net.triangulohackerspace.thsspaceapi.service.Services;
import br.net.triangulohackerspace.thsspaceapi.service.SpacefedService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class SpacefedServiceImpl implements SpacefedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpacefedServiceImpl.class);
    private final SpacefedRepository repository;

    @Autowired
    public SpacefedServiceImpl(final SpacefedRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Spacefed save(@NotNull @Valid final Spacefed spacefed) {
        LOGGER.debug("Creating {}", spacefed);
        Optional<Spacefed> existing = repository.findById(spacefed.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a spacefed with id=%s", spacefed.getId()));
        }
        return repository.save(spacefed);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Spacefed> getList() {
		LOGGER.debug("Retrieving the list of all spacefeds");
        return repository.findAll();
    }

    @Override
    public Services appliesTo() {
        return null;
    }
}
