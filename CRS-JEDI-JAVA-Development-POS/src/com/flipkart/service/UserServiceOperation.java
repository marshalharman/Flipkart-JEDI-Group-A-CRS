package com.flipkart.service;

import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.data.Data;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * Implementations of User Interface
 *
 */
public class UserServiceOperation implements UserInterface{

    UserDAO userDAO = new UserDAOImpl();
    /**
     * Method to verify login credentials of the user
     * @param userID
     * @param password
     * @param role
     * @return boolean if the user exists in the DB
     */
    @Override
    public boolean verifyCredentials(int userID, String password, String role) {

        // find user from data with given username and password
        try {
            return userDAO.login(userID, password, role);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        }
    public void updatePassword(int userID,String password)
    {
         if(userDAO.getUserByID(userID)!=null)
         {
             userDAO.updatePassword(userID, password);
             return;
         }
         System.out.println("User doesn't exist\n");
    }
}
