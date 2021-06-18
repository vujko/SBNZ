package sbnz.integracija.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.facts.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
