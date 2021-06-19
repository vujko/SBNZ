package sbnz.integracija.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sbnz.integracija.example.service.game.RecommendGamesUseCase;
import sbnz.integracija.example.service.notification.GetNotificationsQuery;
import sbnz.integracija.example.facts.Notification;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/notification", produces = "application/json")
public class NotificationController {
	
	private final GetNotificationsQuery getNotificationQuery;
	
	@GetMapping(value="")
	public List<Notification> getNotifications() {
		return this.getNotificationQuery.getNotifications();
	}

}
