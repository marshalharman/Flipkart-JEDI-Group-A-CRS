package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import java.sql.*;
import java.util.*;
public class AdminDAOImpl implements AdminDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_database";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "17102080";

    public void deleteCourse(int courseID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "delete from Course where CourseID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();

            sql = "DELETE FROM Catalog WHERE CourseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();

            sql = "DELETE FROM SemRegistration WHERE CourseID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();

        } catch(SQLException se){
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
    public void addCourse(Course course) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql = "insert into Course(courseId, courseName, catalogId) values (?, ?, ?)";

            stmt.setInt(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());

            stmt.setInt(3, course.getProfID());
            int row = stmt.executeUpdate();
            System.out.println(row + " entries added");
        } catch(SQLException se){
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

    public void approveStudent(int studentId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql="update Student set isApproved = 1 where studentId = ?";
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,studentId);
            int row = stmt.executeUpdate();
            System.out.println(row + " student approved.");
        } catch(SQLException se){
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
    @Override
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "insert into User(userId, name, password, role, gender, address, country) values (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user.getUserID());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole().toString());
            //statement.setBoolean(5, user.isApproved());
            int row = stmt.executeUpdate();
            System.out.println(row + " user added.");
        } catch(SQLException se){
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

    public void addProfessor(Professor professor) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;


        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            this.addUser(professor);
            String sql = "insert into Professor(userId, department, designation) values (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, professor.getUserID());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getDepartment());
            stmt.setString(4, professor.getDesignation());
            int row = stmt.executeUpdate();

            System.out.println(row + " professor added. ");
        } catch(SQLException se){
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
