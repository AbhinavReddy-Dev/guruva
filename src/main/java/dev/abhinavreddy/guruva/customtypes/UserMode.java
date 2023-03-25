package dev.abhinavreddy.guruva.customtypes;

public enum UserMode {
    MENTOR("MENTOR"),
    MENTEE("MENTEE");
    private final String userMode;
    UserMode(String userMode) {
        this.userMode = userMode;
    }
    public String getUserMode() {
        return this.userMode;
    }
}
