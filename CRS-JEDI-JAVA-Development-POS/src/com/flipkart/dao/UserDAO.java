package com.flipkart.dao;

public interface UserDAO {

    // returns UserID if successful
    public boolean login(int userID, String password);


}
