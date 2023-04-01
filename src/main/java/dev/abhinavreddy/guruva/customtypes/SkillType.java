package dev.abhinavreddy.guruva.customtypes;

public enum SkillType {
    NEED_HELP("NEED_HELP"),
    CAN_HELP("CAN_HELP"),
    GOT_HELP("GOT_HELP");
    private final String skillType;
    SkillType(String skillType) {
        this.skillType = skillType;
    }
    public String getSkillType() {
        return this.skillType;
    }
}
