package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

public class DuplicateUserException extends Exception{
    private int userID;
    public DuplicateUserException(int userID) {
        this.userID=userID;
    }
    public String getMessage() {
        return ColourConstant.ANSI_YELLOW+"User with UserID: "+userID+" already exists\n"+ ColourConstant.ANSI_RESET;
    }
}
