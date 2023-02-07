package com.flipkart.exception;

public class DuplicateUserException extends Exception{
    private int userID;
    public DuplicateUserException(int userID) {
        this.userID=userID;
    }
    public String getMessage() {
        return "User with UserID: "+userID+" already exists\n";
    }
}
