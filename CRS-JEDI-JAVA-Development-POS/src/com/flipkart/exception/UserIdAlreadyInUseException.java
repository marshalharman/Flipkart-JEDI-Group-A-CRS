
package com.flipkart.exception;

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
        return "userId: " + userId + " is already in use.";
    }

}