package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    @Override
    public List<Integer> getSemesterList() {
        Connection conn = null;
        PreparedStatement stmt = null;

        List<Integer> semList = new ArrayList<Integer>();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            String sql="insert into employeefc values(?,?,?,?)";
            //String sql = "UPDATE Employees set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);

            String sql1 = "SELECT DISTINCT semID FROM Catalog";
            stmt = conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery(sql1);
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return semList;
    }

//    @Override
//    public void setSemester(int studentID, int semID) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//            System.out.println("Creating statement...");
//            String sql="insert into employeefc values(?,?,?,?)";
//            //String sql = "UPDATE Employees set age=? WHERE id=?";
//            // String sql1="delete from employee where id=?";
//            // stmt.setInt(1, 101);
//
//            String sql1 = "UPDATE from semID FROM Catalog";
//            stmt = conn.prepareStatement(sql1);
//            ResultSet rs = stmt.executeQuery(sql1);
//        }
//        catch(Exception e){
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        }
//    }

    @Override
    public List<Course> getCourses(int semID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            String sql="insert into employeefc values(?,?,?,?)";
            //String sql = "UPDATE Employees set age=? WHERE id=?";
            // String sql1="delete from employee where id=?";
            // stmt.setInt(1, 101);

            String sql1 = "SELECT DISTINCT * FROM Catalog where semID = ?";
            stmt = conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery(sql1);
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return courseList;
    }

    @Override
    public void registerCourses() {

    }

    @Override
    public void dropCourse(int studentID, int courseID) {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM SemRegistration WHERE CourseID = (?) AND StudentID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.setInt(2, studentID);

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

    @Override
    public void getRegisteredCourses(int studentID) {

    }

    @Override
    public HashMap<Course, String> viewGrades(int studentID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        HashMap<Course, String> GradesInCourses = new HashMap<>() ;

        String sql = " SELECT Courses.CourseID AS CourseID, Grade, Name AS CourseName FROM SemRegistration INNER JOIN Courses ON SemRegistration.CourseID = Courses.CourseID WHERE StudentID = 101;";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Course course = new Course();

                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("CourseName"));

                GradesInCourses.put(course,rs.getString("Grade") );
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

        return GradesInCourses;
    }
}
