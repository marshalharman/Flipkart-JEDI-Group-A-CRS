package com.flipkart.dao;

import java.sql.*;

public class UserDAOImpl implements UserDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";



    // login - return bool
    @Override
    public boolean login(int userID, String password, String role) {

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
                if(rs.getString("Role").equalsIgnoreCase(role)) {
                    count++;
                }
            }

            verified = count == 1;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return verified;
    }

    public void register(int userID, String userName, String password, String role, boolean isApproved){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userID);
            stmt.setString(2, userName);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.setBoolean(5, isApproved);

            stmt.executeUpdate();

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
