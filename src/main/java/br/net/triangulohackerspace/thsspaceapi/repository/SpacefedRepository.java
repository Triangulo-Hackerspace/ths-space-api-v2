package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Spacefed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpacefedRepository extends JpaRepository<Spacefed, Long> {
	
	@Query(value = "SELECT spacefed FROM br.net.triangulohackerspace.thsspaceapi.domain.Spacefed spacefed WHERE spacefed.space.id = :spaceId")
	Spacefed findBySpaces(@Param("spaceId") Long spaceId);
}
