package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CacheRepository extends JpaRepository<Cache, Long> {
	
	@Query(value = "SELECT cache FROM br.net.triangulohackerspace.thsspaceapi.domain.Cache cache WHERE cache.space.id = :spaceId")
	Cache findBySpaces(@Param("spaceId") Long spaceId);
}
