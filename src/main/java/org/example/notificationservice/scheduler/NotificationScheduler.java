package org.example.notificationservice.scheduler;

import org.example.notificationservice.model.Notification;
import org.example.notificationservice.repository.NotificationRepository;
import org.example.notificationservice.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationScheduler {
    public NotificationScheduler(NotificationRepository repository,
                                 NotificationService service) {

        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<Notification> due =
                        repository.findDueNotifications(LocalDateTime.now());

                for (Notification n : due) {
                    service.send(n);
                }
            }
        }, 0, 60_000); // every 1 minute
    }
}
