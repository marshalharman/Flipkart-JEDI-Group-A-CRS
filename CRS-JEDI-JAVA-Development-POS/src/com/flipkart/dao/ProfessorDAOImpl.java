package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.SQLException;

public class ProfessorDAOImpl implements ProferssorDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_database";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    static Connection conn = null;


    public List<Course> viewCoursesBySemID(int semID){


        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();

        String sql = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID FROM Courses " +
                "INNER JOIN Catalog ON Courses.CourseID = Catalog.CourseId WHERE Catalog.SemID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

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

        return courseList;
    }

    public boolean registerCourseForProfessor(int profID, String courseName, int semID){

        PreparedStatement stmt = null;

        Course course = getCourseByName(courseName);

        String sql = "UPDATE Courses SET ProfID = (?) WHERE CourseID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, profID);
            stmt.setInt(2, course.getCourseID());

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

        return true;
    }


    public boolean deregisterCourseForProfessor(int profID, String courseName){

        PreparedStatement stmt = null;

        Course course = getCourseByName(courseName);

        String sql = "UPDATE Courses SET ProfID = (?) WHERE CourseID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, -1);
            stmt.setInt(2, course.getCourseID());

            if(course.getProfID() == profID) {
                stmt.executeUpdate();
            }
            else{
                System.out.println("You are not registered for taking that course");
                return false;
            }


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

        return true;
    }

    public List<Student> viewEnrolledStudents(String courseName){

        PreparedStatement stmt = null;

        Course course = getCourseByName(courseName);

        List<Student> studentList = new ArrayList<Student>();

        String sql = "SELECT StudentID FROM SemRegistration WHERE CourseID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int studentID = rs.getInt("StudentID");

                Student student = getStudentByID(studentID);

                studentList.add(student);
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

        return studentList;
    }

    public Student getStudentByID(int studentID){

        PreparedStatement stmt = null;

        Student student = new Student();

        String sql = "Select StudentID, Name, Address, Branch, Degree FROM Student WHERE StudentID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();

            student.setUserID(rs.getInt("StudentID"));
            student.setName(rs.getString("Name"));
            student.setAddress(rs.getString("Address"));
            student.setBranch(rs.getString("Branch"));
            student.setDegree(rs.getString("Degree"));

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

    @Override
    public List<Course> getCoursesByProfessor(int profID) {

        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();

        String sql = "Select CourseID, Name, ProfID FROM Courses WHERE ProfID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, profID);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Course course = new Course();

                course.setCourseID(rs.getInt("CourseID"));
                course.setCourseName(rs.getString("Name"));
                course.setProfID(rs.getInt("ProfID"));

                courseList.add(course);
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

        return courseList;
    }

    @Override
    public Boolean addGrade(int studentId, String courseName, String grade) {

        PreparedStatement stmt = null;

        Course course = getCourseByName(courseName);

        String sql = "UPDATE SemRegistration SET Grade = (?) WHERE StudentID = (?) AND CourseID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, grade);
            stmt.setInt(2, studentId);
            stmt.setInt(3, course.getCourseID());

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

        return true;
    }

    public int getSemIDbyCourseID(int courseID){

        PreparedStatement stmt = null;

        int semID = -1;

        String sql = "SELECT SemID FROM Catalog WHERE CourseId = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);

            ResultSet rs = stmt.executeQuery();

            semID = rs.getInt("SemID");

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

        return semID;
    }

    public Course getCourseByName(String courseName){

        PreparedStatement stmt = null;

        Course course = new Course();

        String sql = "SELECT CourseID, Name, ProfID FROM Courses WHERE Name = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseName);

            ResultSet rs = stmt.executeQuery();

            int cid = rs.getInt("CourseID");
            String name = rs.getString("Name");
            int pid = rs.getInt("ProfID");

            course.setCourseID(cid);
            course.setCourseName(name);
            course.setProfID(pid);

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

        return course;
    }

    @Override
    public Professor getProfessorById(int profID) {

        Professor professor = new Professor();

        PreparedStatement stmt = null;

        String sql = "SELECT ProfID, Name, Department, Designation FROM Professor WHERE ProfID = (?)";

        try{

            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, profID);

            ResultSet rs = stmt.executeQuery();

            int pid = rs.getInt("ProfID");
            String name = rs.getString("Name");
            String department = rs.getString("Department");
            String designation = rs.getString("Designation");

            professor.setUserID(pid);
            professor.setName(name);
            professor.setDepartment(department);
            professor.setDesignation(designation);

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

        return professor;
    }

}
