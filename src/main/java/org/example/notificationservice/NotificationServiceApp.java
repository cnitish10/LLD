package org.example.notificationservice;

import org.example.notificationservice.model.Notification;
import org.example.notificationservice.model.NotificationType;
import org.example.notificationservice.repository.InMemoryNotificationRepository;
import org.example.notificationservice.repository.NotificationRepository;
import org.example.notificationservice.scheduler.NotificationScheduler;
import org.example.notificationservice.service.NotificationService;

import java.time.LocalDateTime;

public class NotificationServiceApp {
    public static void main(String[] args) {
        NotificationRepository repository =
                new InMemoryNotificationRepository();

        NotificationService service =
                new NotificationService(repository);

        new NotificationScheduler(repository, service);

        // Instant notification
        service.create(new Notification(
                "user1",
                NotificationType.EMAIL,
                "Welcome to the platform!",
                null
        ));

        // Scheduled notification
        service.create(new Notification(
                "user1",
                NotificationType.SMS,
                "Your OTP is 1234",
                LocalDateTime.now().plusMinutes(0)
        ));
    }
}
