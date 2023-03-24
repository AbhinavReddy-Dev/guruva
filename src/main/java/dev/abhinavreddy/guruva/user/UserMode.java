package dev.abhinavreddy.guruva.user;

public enum UserMode {
    MENTOR("mentor"),
    MENTEE("mentee");
    private final String userMode;
    UserMode(String userMode) {
        this.userMode = userMode;
    }
    public String getUserMode() {
        return this.userMode;
    }
}
