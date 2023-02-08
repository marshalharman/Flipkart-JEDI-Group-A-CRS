package com.flipkart.exception;

import com.flipkart.constant.Colours;

public class DuplicateUserException extends Exception{
    private int userID;
    public DuplicateUserException(int userID) {
        this.userID=userID;
    }
    public String getMessage() {
        return Colours.ANSI_YELLOW+"User with UserID: "+userID+" already exists\n"+Colours.ANSI_RESET;
    }
}
