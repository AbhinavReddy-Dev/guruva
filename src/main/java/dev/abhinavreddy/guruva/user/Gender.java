package dev.abhinavreddy.guruva.user;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");
    private final String gender;
    Gender(String gender) {
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }
}
