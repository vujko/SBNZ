package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.facts.RegisteredUser;

public interface RegistratedUserRepository extends JpaRepository<RegisteredUser, Long>{

}
