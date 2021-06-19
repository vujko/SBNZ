package sbnz.integracija.example.service.notification;

import java.util.List;

import sbnz.integracija.example.facts.Notification;

public interface GetNotificationsQuery {
	public List<Notification> getNotifications();
}
