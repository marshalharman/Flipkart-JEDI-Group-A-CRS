package com.flipkart.service;

import com.flipkart.data.Data;

public class UserServiceOperation implements UserInterface{

    @Override
    public boolean verifyCredentials(String userName, String password) {

        // find user from data with given username and password
        return true;
    }
}
