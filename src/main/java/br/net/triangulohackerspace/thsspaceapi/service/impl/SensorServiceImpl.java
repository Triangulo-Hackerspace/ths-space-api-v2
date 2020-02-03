package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Sensor;
import br.net.triangulohackerspace.thsspaceapi.repository.SensorRepository;
import br.net.triangulohackerspace.thsspaceapi.service.SensorService;
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
public class SensorServiceImpl implements SensorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorServiceImpl.class);
    private final SensorRepository repository;

    @Autowired
    public SensorServiceImpl(final SensorRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Sensor save(@NotNull @Valid final Sensor sensor) {
        LOGGER.debug("Creating {}", sensor);
        Optional<Sensor> existing = repository.findById(sensor.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a sensor with id=%s", sensor.getId()));
        }
        return repository.save(sensor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sensor> getList() {
		LOGGER.debug("Retrieving the list of all sensors");
        return repository.findAll();
    }

}
