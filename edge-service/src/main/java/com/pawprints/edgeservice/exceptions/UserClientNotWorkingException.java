package com.pawprints.edgeservice.exceptions;

public class UserClientNotWorkingException extends RuntimeException {
    /**
     * Throws an Exception if a user client is down.
     * @param message Throws a custom message to the user.
     */
    public UserClientNotWorkingException(String message) {
        super(message);
    }
}
