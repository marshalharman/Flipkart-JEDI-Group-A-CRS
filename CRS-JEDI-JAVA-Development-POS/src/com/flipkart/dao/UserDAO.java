package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

public interface UserDAO {

    // returns UserID if successful
    public boolean login(int userID, String password, String role) throws UserNotFoundException;

    public void register(int userID,String userName, String password, String role, boolean isApproved);
    public void updatePassword(int userID, String password);
}
