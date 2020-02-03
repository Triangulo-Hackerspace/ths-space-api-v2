package br.net.triangulohackerspace.thsspaceapi.service;

import br.net.triangulohackerspace.thsspaceapi.domain.Space;
import br.net.triangulohackerspace.thsspaceapi.domain.to.SpaceApiTO;

public interface SpaceService extends BusinessService<Space, Long> {
	SpaceApiTO findSpace(Long id);
	Space findById(Long id);
}
