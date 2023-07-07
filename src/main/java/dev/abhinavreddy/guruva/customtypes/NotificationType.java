package dev.abhinavreddy.guruva.customtypes;

public enum NotificationType {

//    user sends a request to mentor to be a mentee
    REQ_USER_TO_MENTOR("REQ_USER_TO_MENTOR"),
    ACC_USER_TO_MENTOR("ACC_USER_TO_MENTOR"),
    REJ_USER_TO_MENTOR("REJ_USER_TO_MENTOR"),
//    user send a request to mentee to be a mentor
    REQ_USER_TO_MENTEE("REQ_USER_TO_MENTEE"),
    ACC_USER_TO_MENTEE("ACC_USER_TO_MENTEE"),
    REJ_USER_TO_MENTEE("REJ_USER_TO_MENTEE"),
//    mentor sends a request to mentee to be a mentor
    REQ_MENTOR_TO_MENTEE("REQ_MENTOR_TO_MENTEE"),
    ACC_MENTOR_TO_MENTEE("ACC_MENTOR_TO_MENTEE"),
    REJ_MENTOR_TO_MENTEE("REJ_MENTOR_TO_MENTEE"),
//    mentee send a request to mentor to be a mentee
    REQ_MENTEE_TO_MENTOR("REQ_MENTEE_TO_MENTOR"),
    ACC_MENTEE_TO_MENTOR("ACC_MENTEE_TO_MENTOR"),
    REJ_MENTEE_TO_MENTOR("REJ_MENTEE_TO_MENTOR"),
//    mentor or mentee sends a feedback to user
    FEEDBACK_MENTOR_TO_USER("FEEDBACK_MENTOR_TO_USER"),
    FEEDBACK_MENTEE_TO_USER("FEEDBACK_MENTEE_TO_USER"),
//    user removes a mentor or mentee
    REM_USER_A_MENTOR_NF_MENTEE("REM_USER_A_MENTOR_NF_MENTEE"),
    REM_USER_A_MENTEE_NF_MENTEE("REM_USER_A_MENTEE_NF_MENTEE"),
//    mentee is closed by mentee or after a given time
    CLOSE_MENTEE_NF_MENTOR("CLOSE_MENTEE_NF_MENTOR"),
    CLOSE_MENTEE_NF_USER("CLOSE_MENTEE_NF_USER");
    private final String notificationType;
    NotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
    public String getNotificationType() {
        return this.notificationType;
    }

}
