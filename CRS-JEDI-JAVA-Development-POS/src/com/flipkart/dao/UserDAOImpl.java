package com.flipkart.dao;

import com.flipkart.bean.User;

import com.flipkart.constant.ColourConstant;
import com.flipkart.constant.ConnectionConstant;
import com.flipkart.exception.UserNotApprovedException;
import com.flipkart.exception.UserNotFoundException;

import java.sql.*;

public class UserDAOImpl implements UserDAO{
    @Override
    public boolean login(int userID, String password, String role) throws UserNotFoundException{

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        User s=getUserByID(userID);
        if(s==null)
        {
            throw new UserNotFoundException(userID);
        }

        boolean verified = false;
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = "SELECT * FROM User WHERE UserID=? AND Password=?";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, userID);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            int count = 0;

            while(rs.next()){
                if( rs.getBoolean("isApproved") == false){
                    throw new UserNotApprovedException(Integer.toString(userID));
                }

                if(rs.getString("Role").equalsIgnoreCase(role) && rs.getBoolean("isApproved")) {
                    count++;
                }
            }

            verified = (count == 1);

        }catch (UserNotApprovedException exception){
            System.out.println(ColourConstant.ANSI_YELLOW+"User " + exception.getUserId() + " not registered! Contact Admin for approval"+ ColourConstant.ANSI_RESET);
        }
        catch(SQLException se){
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

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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
    public void updatePassword(int userID, String password)
    {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE USER SET Password = ? where UserID = ?";
        try {
            Class.forName(ConnectionConstant.JDBC_DRIVER);
            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setInt(2, userID);
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

    public User getUserByID(int userID){

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        User user = null;

        String sql = "SELECT * FROM User WHERE UserID=?";

        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, userID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                user = new User();

                user.setUserID(rs.getInt("UserID"));
                user.setUsername(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setRole(rs.getString("Role"));
                user.setIsApproved(rs.getBoolean("IsApproved"));
            }

            rs.close();

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

        return user;
    }
}
