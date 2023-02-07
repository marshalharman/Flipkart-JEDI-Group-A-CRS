package com.flipkart.exception;

/**
 * Exception to check if user exists
 * @author JEDI-2.0 Team A
 *
 */
public class UserNotFoundException extends Exception {

    private String userId;

    /***
     * Getter function for UserId
     * @param userId
     */
    public UserNotFoundException(String userId) {
        this.userId = userId;
    }

    /**
     * Message thrown by exception
     */
    @Override
    public String getMessage() {
        return "User with userId: " + userId + " not found.";
    }

}