package com.flipkart.service;

public interface PaymentInterface {

    public void pay();
    public void sendNotification(int id,int refid, String msg);
}
