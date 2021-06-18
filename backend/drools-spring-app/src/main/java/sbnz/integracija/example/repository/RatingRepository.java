package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.facts.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long>{

}
