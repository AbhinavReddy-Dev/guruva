package dev.abhinavreddy.guruva.notification;

import dev.abhinavreddy.guruva.customtypes.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification")
public class Notification {
         @Id
         private ObjectId id;
         private String title;
         private String message;
         private NotificationType type;
         private String sender;
         private String receiver;
         private LocalDateTime createdAt;
         private LocalDateTime updatedAt;
         private Boolean isRead;
         private String link;
}
