package com.flipkart.dao;

import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface PaymentDAO {
    public void pay(Student student) throws SQLException;
    public void sendNotification(int id,String transactionID, String msg);
}
