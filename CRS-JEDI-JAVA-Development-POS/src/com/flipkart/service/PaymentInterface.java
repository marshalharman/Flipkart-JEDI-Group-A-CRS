package com.flipkart.service;

import com.flipkart.bean.Student;

public interface PaymentInterface {

    public void pay(Student student);
    public void sendNotification(int id,int refid, String msg);
}
