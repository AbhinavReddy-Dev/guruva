package dev.abhinavreddy.guruva.customtypes;

public enum LearningMode {
    TEXT("TEXT"),
    VIDEO("VIDEO"),
    VOICE("VOICE");
    private final String leaningMode;
    LearningMode(String learningMode) {
        this.leaningMode = learningMode;
    }
    public String getLeaningMode() {
        return this.leaningMode;
    }
}
