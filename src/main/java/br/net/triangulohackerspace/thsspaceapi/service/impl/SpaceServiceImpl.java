package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.*;
import br.net.triangulohackerspace.thsspaceapi.domain.to.ProjectTO;
import br.net.triangulohackerspace.thsspaceapi.domain.to.SensorTO;
import br.net.triangulohackerspace.thsspaceapi.domain.to.SpaceApiTO;
import br.net.triangulohackerspace.thsspaceapi.domain.to.StateTO;
import br.net.triangulohackerspace.thsspaceapi.repository.*;
import br.net.triangulohackerspace.thsspaceapi.service.SpaceService;
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
public class SpaceServiceImpl implements SpaceService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpaceServiceImpl.class);

	@Autowired
	private SpaceRepository repository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private SpacefedRepository spacefedRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private IssueReportChannelsRepository issueReportChannelsRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CacheRepository cacheRepository;

	@Autowired
	private SensorRepository sensorRepository;

	@Autowired
	private TemperatureRepository temperatureRepository;

	@Override
	@Transactional
	public Space save(@NotNull @Valid final Space space) {
		LOGGER.debug("Creating {}", space);
		Optional<Space> existing = repository.findById(space.getId());
		if (!existing.isPresent()) {
			throw new AlreadyExistsException(String.format(
					"There already exists a space with id=%s", space.getId()));
		}
		return repository.save(space);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Space> getList() {
		LOGGER.debug("Retrieving the list of all spaces");
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public SpaceApiTO findSpace(Long id) {
		Optional<Space> space = repository.findById(id);

		SpaceApiTO spaceApiTO = new SpaceApiTO(space.get());

		Location location = locationRepository.findBySpaces(space.get().getId());

		Spacefed spacefed = spacefedRepository.findBySpaces(space.get().getId());

		Contact contact = contactRepository.findBySpaces(space.get().getId());

		IssueReportChannels issueReportChannels = issueReportChannelsRepository
				.findBySpaces(space.get().getId());

		// State state = stateRepository.findBySpaces(space.getId());

		List<State> states = null; //stateRepository.findBySpace(space,
				//new PageRequest(0, 1, Direction.DESC, "id")); //TODO

		Cache cache = cacheRepository.findBySpaces(space.get().getId());

		Sensor sensor = sensorRepository.findBySpaces(space.get().getId());

		List<Temperature> temperatures = temperatureRepository
				.findBySensors(sensor.getId());

		spaceApiTO.setApiVersion(space.get().getApiVersion());
		spaceApiTO.setName(space.get().getName());
		spaceApiTO.setLogo(space.get().getLogo());
		spaceApiTO.setUrl(space.get().getUrl());

		spaceApiTO.setLocation(location);

		spaceApiTO.setSpacefed(spacefed);

		spaceApiTO.setContact(contact);

		spaceApiTO.setIssueReportChannels(issueReportChannels);

		ProjectTO projectTO = new ProjectTO();
		
		List<Project> projects = projectRepository.findAll();
		
		projectTO.setProjects(projects);
		
		spaceApiTO.setProjectTO(projectTO);
		
		spaceApiTO.setCache(cache);

		for (State state : states) {
			StateTO stateTO = new StateTO(state);
			spaceApiTO.setStateTO(stateTO);
		}
		
		SensorTO sensorTO = new SensorTO();

		sensorTO.setSensor(sensor);

		sensorTO.setTemperatures(temperatures);

		spaceApiTO.setSensorTO(sensorTO);

		return spaceApiTO;
	}

	@Override
	@Transactional(readOnly = false)
	public Space findById(Long id) {
		return repository.getOne(id);
	}
}
