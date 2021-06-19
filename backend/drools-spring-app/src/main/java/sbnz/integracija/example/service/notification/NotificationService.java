package sbnz.integracija.example.service.notification;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.facts.Notification;
import sbnz.integracija.example.repository.GameRepository;
import sbnz.integracija.example.repository.NotificationRepository;
import sbnz.integracija.example.repository.PurchaseRepository;
import sbnz.integracija.example.repository.RatingRepository;
import sbnz.integracija.example.repository.RegistratedUserRepository;
import sbnz.integracija.example.security.api.AuthenticationService;
import sbnz.integracija.example.service.knowledge.KnowledgeService;

@Service
@RequiredArgsConstructor
public class NotificationService implements GetNotificationsQuery {
	
	private final NotificationRepository notificationRepo;
	
	@Override
	public List<Notification> getNotifications() {
		return this.notificationRepo.findAll();
	}

}
