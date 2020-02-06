package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Temperature;
import br.net.triangulohackerspace.thsspaceapi.repository.TemperatureRepository;
import br.net.triangulohackerspace.thsspaceapi.service.Services;
import br.net.triangulohackerspace.thsspaceapi.service.TemperatureService;
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
public class TemperatureServiceImpl implements TemperatureService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TemperatureServiceImpl.class);
	private final TemperatureRepository repository;

	@Autowired
	public TemperatureServiceImpl(final TemperatureRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Temperature save(@NotNull @Valid final Temperature temperature) {
		LOGGER.debug("Creating {}", temperature);
		Optional<Temperature> existing = repository.findById(temperature.getId());
		if (!existing.isPresent()) {
			throw new AlreadyExistsException(String.format(
					"There already exists a temperature with id=%s",
					temperature.getId()));
		}
		return repository.save(temperature);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Temperature> getList() {
		LOGGER.debug("Retrieving the list of all temperatures");
		return repository.findAll();
	}

	@Override
	public Services appliesTo() {
		return null;
	}
}
