package com.flipkart.dao;

/**
 *
 * Interface for Payment Dao Operations
 *
 */
public interface PaymentDAO {
    /**
     * Method to get number of courses the student is registered in
     * @param s_id
     * @return number of courses the student is registered in
     * @throws ClassNotFoundException
     */
    public int numOfRegisteredCourses( int s_id) throws ClassNotFoundException;
    /**
     * Method to add upi id of user using sql command
     * @param transID
     * @param upiID
     */
    public void addUPI(String transID, String upiID);
    /**
     * Method to add card details of user using sql command
     * @param cardNumber
     * @param name
     * @param cvv
     * @param expdate
     * @param transID
     */
    public void addCard(String cardNumber, String name, String cvv, String expdate, String transID);
    /**
     * Method to save payment notification details
     * @param transactionID
     * @param msg
     */
    public void saveNotification(String transactionID, String msg);
    /**
     * Method to save payment details in DB
     * @param transID
     * @param s_id
     * @param mode
     * @param amount
     * @param description
     */
    public void savePayment(String transID,int s_id, String mode,int amount,String description);
}
