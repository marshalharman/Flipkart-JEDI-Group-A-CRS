package com.flipkart.dao;
import com.flipkart.bean.User;
import com.flipkart.exception.UserNotApprovedException;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * Interface for User Dao Operations
 *
 */
public interface UserDAO {

    /**
     * Method to verify login using credentials from Database
     * @param userID
     * @param password
     * @param role
     * @return boolean indicating if login is successful
     * @throws UserNotFoundException
     */
    public boolean login(int userID, String password, String role) throws UserNotFoundException;
    /**
     * Method to register a user in Database
     * @param userID
     * @param userName
     * @param password
     * @param role
     * @param isApproved
     */
    public void register(int userID,String userName, String password, String role, boolean isApproved);
    /**
     * Method to update password of a user in Database
     * @param userID
     * @param password
     */
    public void updatePassword(int userID, String password);
    /**
     * Method to get details of user by id from the Database
     * @param userID
     * @return user object with all the details
     */
    public User getUserByID(int userID);

    }
