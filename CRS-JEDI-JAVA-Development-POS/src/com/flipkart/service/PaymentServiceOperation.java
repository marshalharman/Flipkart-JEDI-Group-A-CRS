package com.flipkart.service;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;
import java.util.Scanner;
import java.util.*;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.Math;

public class PaymentServiceOperation implements PaymentInterface {

    @Override
    public void pay(Student student) {
        int s_id = student.getUserID();

        int reg_courses_number = Data.registeredCourses.get(s_id).size();

        int fee_amount = 1000*reg_courses_number;
        System.out.println("Your fee amount = "+fee_amount);
        System.out.println("Select a method to pay -");
        System.out.println("1.UPI   2.Debit/Credit card   3.Cash");

        Scanner obj = new Scanner(System.in);
        int choice;
        choice = Integer.parseInt(obj.nextLine());

        switch (choice) {
            case 1:
                System.out.println("Please enter you upi id: ");
                int upiID = Integer.parseInt(obj.nextLine());
                Data.upiIDS.put(s_id, upiID);
                break;
            case 2:
                System.out.println("Enter card details: ");
                String cardNumber, cvv, expdate;
                cardNumber = obj.nextLine();
                cvv = obj.nextLine();
                expdate = obj.nextLine();
                List<String> temp = new ArrayList<String>();
                temp.add(cardNumber);
                temp.add(cvv);
                temp.add(expdate);
                Data.cardDetails.put(s_id, temp );

                break;
            case 3:
                System.out.println("received amount of "+fee_amount);
                break;
            default:
                System.out.println("Please enter a valid input\n");
        }

        System.out.println("Payment done");

        String uniqueID = UUID.randomUUID().toString();
        sendNotification(s_id,uniqueID,"Payment successful!");
    }

    @Override
    public void sendNotification(int id,String transactionID, String msg) {
        System.out.println("Your payment details...");
        System.out.println("Student ID"+id);
        System.out.println("Reference ID: "+transactionID);
        System.out.println(msg);
    }
}
