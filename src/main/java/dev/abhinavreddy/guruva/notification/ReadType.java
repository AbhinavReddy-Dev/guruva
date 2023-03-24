package dev.abhinavreddy.guruva.notification;

public enum ReadType {
    READ("read"),
    UNREAD("unread");
    private final String readType;
    ReadType(String readType) {
        this.readType = readType;
    }

    public String getReadType() {
        return this.readType;
    }
}
