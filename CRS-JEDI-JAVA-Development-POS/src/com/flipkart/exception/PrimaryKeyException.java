package com.flipkart.exception;

import com.flipkart.constant.Colours;

public class PrimaryKeyException extends Exception{
    private int userID;

    public String getMessage() {
        return Colours.ANSI_YELLOW+"Please provide ID\n"+Colours.ANSI_RESET;
    }
}
