package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

public class UserNotFoundException extends Exception{
    private int userID;
    public UserNotFoundException(int userID) {
        this.userID=userID;
    }
    public String getMessage() {
        return ColourConstant.ANSI_YELLOW+"User with UserID: "+userID+" not found."+ ColourConstant.ANSI_RESET;
    }
}