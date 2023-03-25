package dev.abhinavreddy.guruva.customtypes;

public enum SkillLevel {
    NOVICE("NOVICE"),
    INTERMEDIATE("INTERMEDIATE"),
    SENIOR("SENIOR");
    private final String skillLevel;
    SkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
    public String getSkillLevel() {
        return this.skillLevel;
    }
}
