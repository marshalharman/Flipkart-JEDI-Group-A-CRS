package com.flipkart.dao;

public interface UserDAO {

    // returns UserID if successful
    public boolean login(int userID, String password, String role);

    public void register(int userID,String userName, String password, String role, boolean isApproved);
}
