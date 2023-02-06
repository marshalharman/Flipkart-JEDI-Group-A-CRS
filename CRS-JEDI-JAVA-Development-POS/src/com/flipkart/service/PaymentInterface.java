package com.flipkart.service;
import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface PaymentInterface {

    public void pay(Student student) throws SQLException;
    public void sendNotification(int id,String transactionID, String msg);
}
