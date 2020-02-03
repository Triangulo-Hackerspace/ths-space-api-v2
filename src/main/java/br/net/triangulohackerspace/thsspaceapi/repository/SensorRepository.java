package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
	
	@Query(value = "SELECT sensor FROM br.net.triangulohackerspace.thsspaceapi.domain.Sensor sensor WHERE sensor.space.id = :spaceId")
	Sensor findBySpaces(@Param("spaceId") Long spaceId);
}
