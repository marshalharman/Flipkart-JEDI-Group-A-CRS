package com.flipkart.service;

import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.data.Data;

public class UserServiceOperation implements UserInterface{

    UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean verifyCredentials(int userID, String password) {

        // find user from data with given username and password
        return userDAO.login(userID, password);
    }
}
