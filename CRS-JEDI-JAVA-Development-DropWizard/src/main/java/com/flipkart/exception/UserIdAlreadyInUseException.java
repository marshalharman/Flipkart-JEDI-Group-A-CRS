
package com.flipkart.exception;

import com.flipkart.constant.ColourConstant;

/**
 * @author JEDI-2.0 Team A
 *
 */
public class UserIdAlreadyInUseException extends Exception{
    private Integer userId;


    public UserIdAlreadyInUseException(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setProfessorId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return ColourConstant.ANSI_YELLOW+"userId: " + userId + " is already in use."+ ColourConstant.ANSI_RESET;
    }

}