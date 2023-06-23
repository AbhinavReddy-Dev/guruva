package dev.abhinavreddy.guruva.exceptions;


public class UserAlreadyExists extends RuntimeException{
    private final String message;
    public UserAlreadyExists(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
