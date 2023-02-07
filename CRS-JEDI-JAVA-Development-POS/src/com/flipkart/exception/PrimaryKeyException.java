package com.flipkart.exception;

public class PrimaryKeyException extends Exception{
    private int userID;

    public String getMessage() {
        return "Please provide ID\n";
    }
}
