package dev.abhinavreddy.guruva.customtypes;

public enum FeedbackType {
    MENTEE_TO_MENTOR("MENTEE_TO_MENTOR"),
    MENTOR_TO_MENTEE ("MENTOR_TO_MENTEE");

    private final String feedbackType;

    FeedbackType(String type) {
        this.feedbackType = type;
    }

    public String getFeedbackType() {
        return this.feedbackType;
    }
}
