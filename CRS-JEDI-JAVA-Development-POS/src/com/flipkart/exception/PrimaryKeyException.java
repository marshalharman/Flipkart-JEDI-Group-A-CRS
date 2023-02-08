package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

public class PrimaryKeyException extends Exception{
    private int userID;

    public String getMessage() {
        return ColourConstant.ANSI_YELLOW+"Please provide ID\n"+ ColourConstant.ANSI_RESET;
    }
}
