package com.flipkart.service;
import com.flipkart.bean.Student;


/**
 *
 * Interface for Payment Service Operations
 *
 */

public interface PaymentInterface {

    /**
     * Method to pay fees
     * @param studentID
     */
    public void pay(int studentID);
    /**
     * Method to send payment notification to the student
     * @param id
     * @param transactionID
     * @param msg
     */
    public void sendNotification(int id,String transactionID, String msg);
}
