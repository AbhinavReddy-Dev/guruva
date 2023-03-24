package dev.abhinavreddy.guruva.user;

public enum Mode {
    TEXT("text"),
    VIDEO("video"),
    VOICE("voice");
    private final String modeType;
    Mode(String modeType) {
        this.modeType = modeType;
    }
    public String getModeType() {
        return this.modeType;
    }
}
