package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface PaymentDAO {
    public int numOfRegisteredCourses( int s_id) throws ClassNotFoundException;
    public void addUPI(String transID, String upiID);

    public void addCard(String cardNumber, String name, String cvv, String expdate, String transID);

    public void saveNotification(String transactionID, String msg);

}
