package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_database";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "17102080";

    public void register(int studentID, String name, String address, String username, String password, String branch, String degree){
        Connection conn = null;
        PreparedStatement stmt = null;

        String role = "student";
        String sql1 = "INSERT INTO User VALUES (?,?,?,?,?);";

        String sql2 = "INSERT INTO Student (StudentID, Name, Address, Branch, Degree) VALUES (?,?,?,?,?);";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");

            stmt = conn.prepareStatement(sql1);
            stmt.setInt(1,studentID);
            stmt.setString(2,username);
            stmt.setString(3,password);
            stmt.setString(4,role);
            stmt.setInt(5,0);

            stmt.executeUpdate();

            stmt = conn.prepareStatement(sql2);
            stmt.setInt(1,studentID);
            stmt.setString(2,name);
            stmt.setString(3,address);
            stmt.setString(4,branch);
            stmt.setString(5,degree);

            stmt.executeUpdate();
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

    }
    @Override
    public List<Integer> getSemesterList() {
        Connection conn = null;
        PreparedStatement stmt = null;

        List<Integer> semList = new ArrayList<Integer>();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql1 = "SELECT DISTINCT semID FROM Catalog";
            stmt = conn.prepareStatement(sql1);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                int semID = rs.getInt(1);
                semList.add(semID);
            }
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
        return semList;
    }

    public void setSemIDforStudent(int studentID, int semID){
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE Student SET SemID = ? WHERE StudentID = ?";

        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, semID);
            stmt.setInt(2, studentID);

            stmt.executeUpdate();

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

    }

    @Override
    public List<Course> getCourses(int semID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID " +
                    "FROM Catalog INNER JOIN Courses ON Catalog.CourseId = Courses.CourseID WHERE semID = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, semID);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Course course = new Course();

                course.setCourseID(rs.getInt("Courses.CourseID"));
                course.setCourseName(rs.getString("Courses.Name"));
                course.setProfID(rs.getInt("Courses.ProfID"));

                courseList.add(course);
            }
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

        return courseList;
    }

    @Override
    public HashMap<Integer, Integer> getCourseEnrollmentCount(int semID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        HashMap<Integer, Integer> courseEnrollmentCount = new HashMap<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            System.out.println("Creating statement...");

            String sql = "SELECT CourseID, COUNT(studentID) AS Frequency FROM SemRegistration GROUP BY CourseID";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int courseID = rs.getInt("CourseID");
                int count = rs.getInt("Frequency");

                System.out.println(courseID + ":" + count);

                courseEnrollmentCount.put(courseID, count);
            }
            rs.close();
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
        return courseEnrollmentCount;
    }

    @Override
    public void registerCourses(int studentID, List<Integer> courseIDs, int semID){

        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "INSERT INTO SemRegistration (`StudentID`, `CourseID`, `SemID`) VALUES  (?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1,studentID);
            stmt.setInt(3, semID);
            for(int courseID : courseIDs){
                stmt.setInt(2, courseID);
                stmt.executeUpdate();
            }
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
    public List<Course> getRegisteredCourses(int studentID) {
        Connection conn = null;
        PreparedStatement stmt = null;


        List<Course> registeredCourse = new ArrayList<>();

        String sql = " SELECT Courses.CourseID, Courses.Name, Courses.ProfID FROM SemRegistration INNER JOIN Courses ON SemRegistration.CourseID = Courses.CourseID WHERE StudentID = ?";
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                Course regCourse = new Course();

                regCourse.setCourseID(rs.getInt("Courses.CourseID"));
                regCourse.setCourseName(rs.getString("Courses.Name"));
                regCourse.setCourseName(rs.getString("Courses.ProfID"));

                System.out.println(studentID + ":" + regCourse.getCourseName() + ":" + regCourse.getCourseID() + ":" + regCourse.getProfID());

                registeredCourse.add(regCourse);
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

        return registeredCourse;
    }

    @Override
    public HashMap<Course, String> viewGrades(int studentID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        HashMap<Course, String> GradesInCourses = new HashMap<>() ;

        String sql1 = "select GradesEnabled from Student where StudentID=(?);";


        String sql = "SELECT Courses.CourseID AS CourseID, Grade, Name AS CourseName FROM SemRegistration INNER JOIN Courses ON SemRegistration.CourseID = Courses.CourseID WHERE StudentID = ?;";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql1);
            stmt.setInt(1,studentID);

            ResultSet rs1 =stmt.executeQuery();

            rs1.next();
            if(!rs1.getBoolean("GradesEnabled")) {
                return GradesInCourses;
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();

            System.out.println("GRADE CARD:");
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


    public Student getStudentByID(int studentID){

        PreparedStatement stmt = null;
        Connection conn = null;

        Student student = new Student();

        String sql = "Select StudentID, Name, Address, Branch, Degree, SemID FROM Student WHERE StudentID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            student.setUserID(rs.getInt("StudentID"));
            student.setName(rs.getString("Name"));
            student.setAddress(rs.getString("Address"));
            student.setBranch(rs.getString("Branch"));
            student.setDegree(rs.getString("Degree"));
            student.setSemID(rs.getInt("SemID"));

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

        return student;
    }
}
