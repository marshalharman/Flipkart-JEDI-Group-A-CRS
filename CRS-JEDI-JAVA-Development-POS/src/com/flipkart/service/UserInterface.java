package com.flipkart.service;
/**
 *
 * Interface for User Service Operations
 *
 */
public interface UserInterface {
    /**
     * Method to verify login credentials of the user
     * @param userID
     * @param password
     * @param role
     * @return boolean if the user exists in the DB
     */
    public boolean verifyCredentials(int userID, String password, String role);
    public void updatePassword(int userID,String password);

}
