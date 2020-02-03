package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
	
	@Query(value = "SELECT temperature FROM br.net.triangulohackerspace.thsspaceapi.domain.Temperature temperature WHERE temperature.sensor.id = :sensorId")
	List<Temperature> findBySensors(@Param("sensorId") Long sensorId);
}
