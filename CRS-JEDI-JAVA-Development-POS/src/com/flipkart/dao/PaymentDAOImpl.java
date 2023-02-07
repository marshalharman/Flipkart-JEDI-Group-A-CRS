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
    static final String DB_URL = "jdbc:mysql://localhost/crs_database";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "LIKITHl1.#";
    Connection connection = null;
    PreparedStatement statement = null;
    String sql ="";

    public int numOfRegisteredCourses( int s_id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            String mode = "";
            String sql = "select count(*) from SemRegistration where StudentID = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, s_id);
            ResultSet result = statement.executeQuery();
            result.next();
            return result.getInt(1);
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return -1;
    }
    public void addUPI(String transID, String upiID)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = null;
            sql = "insert into UPI values(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, transID);
            statement.setString(2, upiID);
            statement.executeUpdate();
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    public void addCard(String cardNumber, String name, String cvv, String expdate, String transID)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = null;
            sql = "insert into CardDetails values(?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, transID);
            statement.setString(2, cardNumber);
            statement.setString(3, name);
            statement.setString(4, expdate);
            statement.executeQuery();
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    public void savePayment(String transID,int s_id, String mode,int amount,String description)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = null;
            sql = "insert into Payment values(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, transID);
            statement.setInt(2, s_id);
            statement.setString(3, mode);
            statement.setInt(4,amount);
            statement.setString(5, description);
            statement.executeUpdate();
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    public void saveNotification(String transactionID, String msg)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            String notificationID = UUID.randomUUID().toString();
            statement = null;
            String sql = "insert into Notification values(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, notificationID);
            statement.setString(2,  msg);
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
