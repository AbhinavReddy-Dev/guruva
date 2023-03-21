package dev.abhinavreddy.guruva.notification;

public enum NotificationType {
//    A request to be a mentor has been sent to you
    MENTOR_REQUEST,
    MENTOR_REQUEST_ACCEPTED,
    MENTOR_REQUEST_REJECTED,
//    A request to be a mentee has been sent to you
    MENTEE_REQUEST,
    MENTEE_REQUEST_ACCEPTED,
    MENTEE_REQUEST_REJECTED,
//    A message has been sent to you
    MESSAGE,
//    A session request has been sent to you
    SESSION_REQUEST,
    SESSION_REQUEST_ACCEPTED,
    SESSION_REQUEST_REJECTED,
}
