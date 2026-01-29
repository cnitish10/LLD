package org.example.notificationservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {

    private final String id;
    private final String userId;
    private final NotificationType type;
    private final String content;
    private NotificationStatus status;
    private final LocalDateTime scheduledAt;
    private final LocalDateTime createdAt;

    public Notification(String userId,
                        NotificationType type,
                        String content,
                        LocalDateTime scheduledAt) {

        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.type = type;
        this.content = content;
        this.scheduledAt = scheduledAt;
        this.createdAt = LocalDateTime.now();
        this.status = scheduledAt == null
                ? NotificationStatus.PENDING
                : NotificationStatus.SCHEDULED;
    }

    // Getters & setters
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public NotificationType getType() { return type; }
    public String getContent() { return content; }
    public NotificationStatus getStatus() { return status; }
    public void setStatus(NotificationStatus status) { this.status = status; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
