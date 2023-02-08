package com.flipkart.service;

import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.UserNotFoundException;

public class UserServiceOperation implements UserInterface{

    UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean verifyCredentials(int userID, String password, String role) {

        // find user from data with given username and password
        try {
            boolean verified = userDAO.login(userID, password, role);
            return verified;
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void updatePassword(int userID,String password)
    {
         if(userDAO.getUserByID(userID) != null )
         {
             userDAO.updatePassword(userID, password);
             return;
         }
         System.out.println("User doesn't exist\n");
         return;
    }
}
