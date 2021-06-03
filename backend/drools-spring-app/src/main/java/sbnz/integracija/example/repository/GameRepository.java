package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.facts.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
