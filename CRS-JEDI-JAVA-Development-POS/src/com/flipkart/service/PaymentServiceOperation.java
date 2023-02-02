package com.flipkart.service;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.Math;
import java.util.Map;

public class PaymentServiceOperation implements PaymentInterface {

    @Override
    public void pay(Student student) {
        int s_id = student.getUserID();

        int reg_courses_number = Data.registeredCourses.get(s_id).size();

        int fee_amount = 1000*reg_courses_number;
        System.out.println("Your fee amount = "+fee_amount);
        System.out.println("Select a method to pay -");
        System.out.println("1.UPI   2.Cheque");

        System.out.println("Payment done");



        sendNotification(s_id,6723154,"Payment succesfull");
    }

    @Override
    public void sendNotification(int id,int transactionID, String msg) {
        System.out.println("Your payment details...");
        System.out.println("Student ID"+id);
        System.out.println("Reference ID: "+transactionID);
        System.out.println(msg);
    }
}
