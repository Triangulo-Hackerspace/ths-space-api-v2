package br.net.triangulohackerspace.thsspaceapi.service;

import br.net.triangulohackerspace.thsspaceapi.domain.State;
import br.net.triangulohackerspace.thsspaceapi.domain.to.StateTO;

import java.util.List;

public interface StateService extends BusinessService<State, Long> {
	
	State saveByUser(State state, Long userId, String entry);
	
	List<StateTO> getStateList();
	
	StateTO findState();
}
