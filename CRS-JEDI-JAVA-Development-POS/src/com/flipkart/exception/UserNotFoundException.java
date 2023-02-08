package com.flipkart.exception;

import com.flipkart.constant.Colours;

public class UserNotFoundException extends Exception{
    private int userID;
    public UserNotFoundException(int userID) {
        this.userID=userID;
    }
    public String getMessage() {
        return Colours.ANSI_YELLOW+"User with UserID: "+userID+" not found."+Colours.ANSI_RESET;
    }
}