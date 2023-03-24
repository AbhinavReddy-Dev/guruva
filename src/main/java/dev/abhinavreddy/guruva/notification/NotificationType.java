package dev.abhinavreddy.guruva.notification;

public enum NotificationType {
//    A request to be a mentor has been sent to you
    MENTOR_REQUEST("mentor_request"),
    MENTOR_REQUEST_ACCEPTED("mentor_request_accepted"),
    MENTOR_REQUEST_REJECTED("mentor_request_rejected"),
//    A request to be a mentee has been sent to you
    MENTEE_REQUEST("mentee_request"),
    MENTEE_REQUEST_ACCEPTED("mentee_request_accepted"),
    MENTEE_REQUEST_REJECTED("mentee_request_rejected"),
//    A message has been sent to you
    MESSAGE("message"),
//    A session request has been sent to you
    SESSION_REQUEST("session_request"),
    SESSION_REQUEST_ACCEPTED("session_request_accepted"),
    SESSION_REQUEST_REJECTED("session_request_rejected");
    private final String notificationType;
    NotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
    public String getNotificationType() {
        return this.notificationType;
    }

}
