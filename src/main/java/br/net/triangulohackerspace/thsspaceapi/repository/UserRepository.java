package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
