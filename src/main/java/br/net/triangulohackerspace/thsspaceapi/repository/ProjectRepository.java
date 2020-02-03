package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query(value = "SELECT project FROM br.net.triangulohackerspace.thsspaceapi.domain.Project project WHERE project.space.id = :spaceId")
	Project findBySpaces(@Param("spaceId") Long spaceId);
}
