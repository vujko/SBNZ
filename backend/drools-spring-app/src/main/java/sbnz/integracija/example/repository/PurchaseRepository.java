package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.facts.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
