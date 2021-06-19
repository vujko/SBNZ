package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sbnz.integracija.example.facts.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
