package com.flipkart.service;
import com.flipkart.bean.Student;
import com.flipkart.dao.PaymentDAOImpl;
import com.flipkart.data.Data;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.Math;

public class PaymentServiceOperation implements PaymentInterface {

    @Override
    public void pay(Student student) throws SQLException {
        PaymentDAOImpl payment_dao = new PaymentDAOImpl();
        try {
            payment_dao.pay(student);
        } catch (SQLException e) {
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNotification(int id,String transactionID, String msg) {
        System.out.println("Your payment details...");
        System.out.println("Student ID"+id);
        System.out.println("Reference ID: "+transactionID);
        System.out.println(msg);
    }
}
