package dev.abhinavreddy.guruva.user;

public enum SkillLevel {
    NOVICE("novice"),
    INTERMEDIATE("intermediate"),
    SENIOR("senior");
    private final String skillLevel;
    SkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
    public String getSkillLevel() {
        return this.skillLevel;
    }
}
