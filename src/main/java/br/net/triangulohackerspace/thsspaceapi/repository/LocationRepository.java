package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
	@Query(value = "SELECT location FROM br.net.triangulohackerspace.thsspaceapi.domain.Location location WHERE location.space.id = :spaceId")
	Location findBySpaces(@Param("spaceId") Long spaceId);
}
