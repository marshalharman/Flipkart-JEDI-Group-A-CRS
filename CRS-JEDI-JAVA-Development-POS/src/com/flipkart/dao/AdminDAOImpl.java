package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.User;
import com.flipkart.constant.Dao;
import com.flipkart.exception.*;

import java.sql.*;
import java.util.*;
public class AdminDAOImpl implements AdminDAO {



    public void addAdmin(int userID, String name){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO Admin VALUES (?, ?)";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Dao.DB_URL,Dao.USER,Dao.PASS);

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

    @Override
    public void deleteCourse(int courseID) throws CourseNotDeletedException , CourseNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Dao.DB_URL,Dao.USER,Dao.PASS);

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
            int row=stmt.executeUpdate();
            if(row==0){
                System.out.println(courseID + " not in catalog!");
                throw new CourseNotFoundException(courseID);
            }

        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            throw new CourseNotDeletedException(courseID);
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
    public void addCourse(Course course, int semID)throws CourseAlreadyPresentException{

        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(Dao.DB_URL,Dao.USER,Dao.PASS);

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
            throw new CourseAlreadyPresentException(course.getCourseID());

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

    public void approveStudent(int studentId) throws StudentNotFoundForApprovalException {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql="UPDATE User SET isApproved = 1 WHERE UserId = ?";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(Dao.DB_URL,Dao.USER,Dao.PASS);

            stmt=conn.prepareStatement(sql);
            stmt.setInt(1,studentId);
            int row = stmt.executeUpdate();
            if(row==0)
            {
                System.out.println("Student with "+studentId+" not found.");
                throw new StudentNotFoundForApprovalException(studentId);
            }

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

    public void addProfessor(Professor professor) throws ProfessorNotAddedException,UserIdAlreadyInUseException {
        Connection conn = null;
        PreparedStatement stmt = null;


        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(Dao.DB_URL,Dao.USER,Dao.PASS);

            String sql = "INSERT INTO Professor(ProfId, Name, Department, Designation) values (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, professor.getUserID());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getDepartment());
            stmt.setString(4, professor.getDesignation());
            int row = stmt.executeUpdate();
            if(row==0)
            {
                System.out.println("Professor not added.");
                throw new ProfessorNotAddedException(professor.getUserID());

            }

        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            throw new UserIdAlreadyInUseException(professor.getUserID());
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

            conn = DriverManager.getConnection(Dao.DB_URL,Dao.USER,Dao.PASS);

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
