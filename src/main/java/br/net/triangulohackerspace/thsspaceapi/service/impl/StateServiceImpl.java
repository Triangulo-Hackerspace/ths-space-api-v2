package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.State;
import br.net.triangulohackerspace.thsspaceapi.domain.StateStatus;
import br.net.triangulohackerspace.thsspaceapi.domain.User;
import br.net.triangulohackerspace.thsspaceapi.domain.to.StateTO;
import br.net.triangulohackerspace.thsspaceapi.repository.StateRepository;
import br.net.triangulohackerspace.thsspaceapi.repository.UserRepository;
import br.net.triangulohackerspace.thsspaceapi.service.Services;
import br.net.triangulohackerspace.thsspaceapi.service.StateService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StateServiceImpl.class);

	@Autowired
	private StateRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public StateServiceImpl(StateRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public State saveByUser(@NotNull @Valid State state, Long userId,
							String entry) {

		Optional<User> user = userRepository.findById(userId);
		state.setUser(user.get());

		if (entry.equals("OPEN")) { // [TODO] Melhorar buscar por enum
			state.setStatus(StateStatus.OPEN.getStatus());
		} else if (entry.equals("CLOSE")) {
			state.setStatus(StateStatus.CLOSE.getStatus());
		}

		LOGGER.debug("Creating {}", state);
		Optional<State> existing = repository.findById(state.getId());
		if (existing != null) {
			throw new AlreadyExistsException(String.format(
					"There already exists a state with id=%s", state.getId()));
		}
		return repository.save(state);
	}

	@Override
	@Transactional(readOnly = false)
	public List<StateTO> getStateList() {
		LOGGER.debug("Retrieving the list of all states");

		List<StateTO> stateResults = new ArrayList<>();
		StateTO stateResult = null;

		List<State> states = repository.findAll();

		for (State state : states) {
			stateResult = new StateTO(state);

			// stateResult.setDate(state.getDate());
			// stateResult.setOpen(state.getOpen());
			// stateResult.setSpace(state.getSpace());
			// stateResult.setStateStatus(state.getStateStatus());
			// stateResult.setUser(state.getUser());

			stateResults.add(stateResult);
		}

		return stateResults;
	}

	@Override
	public State save(@NotNull @Valid State state) {
		LOGGER.debug("Creating {}", state);
		Optional<State> existing = repository.findById(state.getId());
		if (existing != null) {
			throw new AlreadyExistsException(String.format(
					"There already exists a state with id=%s", state.getId()));
		}
		return repository.save(state);
	}

	@Override
	@Transactional(readOnly = true)
	public List<State> getList() {
		LOGGER.debug("Retrieving the list of all spaces");
		return repository.findAll();
	}

	@Override
	public Services appliesTo() {
		return null;
	}

	@Override
	public StateTO findState() {
		StateTO stateTO = null;
		
		State result = repository.findAll(sortByDateAsc()).get(0);
		
		System.out.println("" + result.getStatus());
		System.out.println("" + result.getDate());
		stateTO = new StateTO(result);
		
		stateTO.setOpen(result.getStatus());
		stateTO.setDate(result.getDate());
				
		return stateTO;
	}
	
	  /**
     * Returns a Sort object which sorts persons in ascending order by using the last name.
     * @return
     */
    private Sort sortByDateAsc() {
        //return new Sort(Sort.Direction.ASC, "date"); //TODO
		return null;
    }
}
