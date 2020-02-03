package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Space;
import br.net.triangulohackerspace.thsspaceapi.domain.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
	
	@Query(value = "SELECT state FROM br.net.triangulohackerspace.thsspaceapi.domain.State state WHERE state.space.id = :spaceId")
	State findBySpaces(@Param("spaceId") Long spaceId);
	
	List<State> findBySpace(Space space, Pageable page);
}
