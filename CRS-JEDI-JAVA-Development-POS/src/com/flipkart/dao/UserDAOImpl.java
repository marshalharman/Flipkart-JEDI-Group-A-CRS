package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";



    // login - return bool
    @Override
    public boolean login(int userID, String password) {

        Connection conn = null;
        PreparedStatement stmt = null;

        boolean verified = false;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            String sql="insert into employeefc values(?,?,?,?)";
            //String sql = "UPDATE Employees set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);

            String sql1 = "SELECT * FROM User WHERE userID = ? AND password = ?";
            stmt = conn.prepareStatement(sql1);

            stmt.setInt(1, 101);
            stmt.setString(2, "Harman");
//            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery(sql1);

            int count = 0;

            while(rs.next()){
                count++;
            }

            verified = count == 1;
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return verified;
    }
}
