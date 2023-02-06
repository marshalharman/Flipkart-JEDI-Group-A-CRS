package com.flipkart.service;
import com.flipkart.bean.Student;

public interface PaymentInterface {

    public void pay(int studentID);
    public void sendNotification(int id,String transactionID, String msg);
}
