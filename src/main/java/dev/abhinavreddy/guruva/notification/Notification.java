package dev.abhinavreddy.guruva.notification;

import dev.abhinavreddy.guruva.user.User;

import java.time.LocalDateTime;

public record Notification(
        String id,
        String title,
        String body,
        NotificationType type,
        User sender,
        User receiver,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
