package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	@Query(value = "SELECT contact FROM br.net.triangulohackerspace.thsspaceapi.domain.Contact contact WHERE contact.space.id = :spaceId")
	Contact findBySpaces(@Param("spaceId") Long spaceId);
}
