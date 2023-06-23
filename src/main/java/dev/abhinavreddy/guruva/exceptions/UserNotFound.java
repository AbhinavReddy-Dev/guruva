package dev.abhinavreddy.guruva.exceptions;

public class UserNotFound extends RuntimeException{
    private final String message;
    public UserNotFound(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
