package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Location;
import br.net.triangulohackerspace.thsspaceapi.repository.LocationRepository;
import br.net.triangulohackerspace.thsspaceapi.service.LocationService;
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
public class LocationServiceImpl implements LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);
    private final LocationRepository repository;

    @Autowired
    public LocationServiceImpl(final LocationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Location save(@NotNull @Valid final Location location) {
        LOGGER.debug("Creating {}", location);
        Optional<Location> existing = repository.findById(location.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a location with id=%s", location.getId()));
        }
        return repository.save(location);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> getList() {
		LOGGER.debug("Retrieving the list of all locations");
        return repository.findAll();
    }
}
