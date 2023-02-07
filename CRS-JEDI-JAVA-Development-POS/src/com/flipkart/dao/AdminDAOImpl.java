package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import java.sql.*;
import java.util.*;
public class AdminDAOImpl implements AdminDAO {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_database";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "LIKITHl1.#";

    public void addAdmin(int userID, String name){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Admin VALUES (?, ?)";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, userID);
            stmt.setString(2, name);

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

    public void deleteCourse(int courseID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "DELETE FROM Catalog WHERE CourseId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();

            sql = "DELETE FROM SemRegistration WHERE CourseID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();



            sql = "DELETE FROM Courses WHERE CourseID = ?";
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
    public void addCourse(Course course, int semID){

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "INSERT INTO Courses(CourseID, Name) VALUES (?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());
            stmt.executeUpdate();


            sql = "INSERT INTO Catalog(CourseId, SemID) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());
            stmt.setInt(2, semID);
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

    public void approveStudent(int studentId){
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql="UPDATE User SET isApproved = 1 WHERE UserId = ?";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,studentId);
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

    public void addProfessor(Professor professor){
        Connection conn = null;
        PreparedStatement stmt = null;


        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO Professor(ProfId, Name, Department, Designation) values (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, professor.getUserID());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getDepartment());
            stmt.setString(4, professor.getDesignation());
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

    public void generateGradeCard(){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Student SET GradesEnabled = ? WHERE GradesEnabled = ?";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            stmt.setInt(2, 0);

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
}
