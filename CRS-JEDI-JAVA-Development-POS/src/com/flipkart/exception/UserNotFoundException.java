package com.flipkart.exception;

public class UserNotFoundException extends Exception{
    private int userID;
    public UserNotFoundException(int userID) {
        this.userID=userID;
    }
    public String getMessage() {
        return "User with UserID: "+userID+" not found.";
    }
}