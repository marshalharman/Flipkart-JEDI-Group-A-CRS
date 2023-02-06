package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.data.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaymentDAOImpl implements PaymentDAO{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    Connection connection = null;
    PreparedStatement statement = null;
    public void pay(Student student) throws SQLException {


        String uniqueID = UUID.randomUUID().toString();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            int s_id = student.getUserID();
            String mode = "";
            String sql = "select count(*) from SemRegistration where StudentID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, s_id);
            ResultSet result = statement.executeQuery();
            int reg_courses_number = result.getInt(1);

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
                    statement = null;
                    sql = "insert into UPI values(?,?)";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1,uniqueID);
                    statement.setString(2,upiID);
                    statement.executeQuery();
                    mode= "UPI";
                    break;
                case 2:
                    System.out.println("Enter card details: ");
                    String cardNumber, name,cvv, expdate;
                    cardNumber = obj.nextLine();
                    name = obj.nextLine();
                    cvv = obj.nextLine();
                    expdate = obj.nextLine();
                    statement = null;
                    sql = "insert into CardDetails values(?,?,?,?)";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1,uniqueID);
                    statement.setString(2,cardNumber);
                    statement.setString(3,name);
                    statement.setString(4,expdate);
                    statement.executeQuery();
                    mode = "CARD";
                    break;
                case 3:
                    System.out.println("received amount of " + fee_amount);
                    mode = "CASH";
                    break;
                default:
                    System.out.println("Please enter a valid input\n");
            }
            System.out.println("Payment done");
            statement = null;
            sql = "insert into Payment values(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,uniqueID);
            statement.setInt(2,s_id);
            statement.setString(3, mode);
            statement.setInt(4,fee_amount);
            statement.setString(5,"payment successful");
            statement.executeQuery();
            sendNotification(s_id, uniqueID, "Payment successful!");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    public void sendNotification(int id,String transactionID, String msg)
    {
        try {
            String notificationID = UUID.randomUUID().toString();
            System.out.println("Your payment details...");
            System.out.println("Student ID" + id);
            System.out.println("Reference ID: " + transactionID);
            System.out.println(msg);
            statement = null;
            String sql = "insert into Notification values(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, notificationID);
            statement.setString(2, "notification sent");
            statement.setString(3,transactionID);
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
}
