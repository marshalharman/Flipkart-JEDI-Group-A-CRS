package com.flipkart.service;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.lang.Math;

public class PaymentServiceOperation implements PaymentInterface {

    @Override
    public void pay() {

    }

    @Override
    public void sendNotification(int id,int transactionID, String msg) {
        System.out.println("Your payment details...");
        System.out.println("Student ID"+id);
        System.out.println("Reference ID: "+transactionID);
        System.out.println(msg);
    }
}
