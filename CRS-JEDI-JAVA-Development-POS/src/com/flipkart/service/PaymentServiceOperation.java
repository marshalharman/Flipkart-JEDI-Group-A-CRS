package com.flipkart.service;
import com.flipkart.bean.Student;
import com.flipkart.dao.PaymentDAOImpl;
import com.flipkart.data.Data;
import java.util.Scanner;
import java.util.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.Math;

public class PaymentServiceOperation implements PaymentInterface {

    @Override
    public void pay(Student student) {
        String uniqID = UUID.randomUUID().toString();
        String mode = "";
        PaymentDAOImpl paydao = new PaymentDAOImpl();
        int reg_courses_number = paydao.numOfRegisteredCourses(student.getUserID());
        int fee_amount = 1000 * reg_courses_number;
        System.out.println("Your fee amount = " + fee_amount);
        System.out.println("Select a method to pay -");
        System.out.println("1.UPI   2.Debit/Credit card   3.Cash");
        Scanner obj = new Scanner(System.in);
        int choice;
        choice = Integer.parseInt(obj.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Please enter you upi id: ");
                String upiID = obj.nextLine();
                paydao.addUPI(uniqID, upiID);
                mode = "UPI";
                break;
            case 2:
                System.out.println("Enter card details: ");
                String cardNumber, name, cvv, expdate;
                cardNumber = obj.nextLine();
                name = obj.nextLine();
                cvv = obj.nextLine();
                expdate = obj.nextLine();
                paydao.addCard(cardNumber, name, cvv, expdate, uniqID);
                mode = "CARD";
                break;
            case 3:
                System.out.println("received amount of " + fee_amount);
                mode = "CASH";
                break;
            default:
                System.out.println("Please enter a valid input\n");

        }
        paydao.savePayment(uniqID,student.getUserID(),mode,fee_amount,"payment successful");
    }

    @Override
    public void sendNotification(int id,String transactionID, String msg) {
        System.out.println("Your payment details...");
        System.out.println("Student ID"+id);
        System.out.println("Reference ID: "+transactionID);
        System.out.println(msg);
        PaymentDAOImpl paydao = new PaymentDAOImpl();
        paydao.saveNotification(transactionID,msg);
    }
}
