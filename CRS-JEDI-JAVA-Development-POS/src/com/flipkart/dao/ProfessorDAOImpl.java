package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.ConnectionConstant;
import com.flipkart.exception.CourseNotFoundByNameException;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.SQLException;

public class ProfessorDAOImpl implements ProferssorDAO{

    static java.sql.Connection conn = null;

    public List<Course> viewCoursesBySemID(int semID){


        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();

        String sql = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID FROM Courses " +
                "INNER JOIN Catalog ON Courses.CourseID = Catalog.CourseId WHERE Catalog.SemID = (?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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

    public boolean registerCourseForProfessor(int profID, String courseName, int semID) throws CourseNotFoundByNameException {

        PreparedStatement stmt = null;
        Course course = null;

        course = getCourseByName(courseName);
        if( course == null ){ throw new CourseNotFoundByNameException(courseName); }

        String sql = "UPDATE Courses SET ProfID = (?) WHERE CourseID = (?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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


    public boolean deregisterCourseForProfessor(int profID, String courseName) throws CourseNotFoundByNameException{

        PreparedStatement stmt = null;

        Course course = getCourseByName(courseName);
        if( course == null ){ throw new CourseNotFoundByNameException(courseName); }
        String sql = "UPDATE Courses SET ProfID = (?) WHERE CourseID = (?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, -1);
            stmt.setInt(2, course.getCourseID());

            if( course.getProfID() == profID) {
                stmt.executeUpdate();
            }
            else{
                System.out.println("You are not registered to take this course");
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

    public List<Student> viewEnrolledStudents(String courseName) throws CourseNotFoundByNameException{

        PreparedStatement stmt = null;

        List<Student> studentList = new ArrayList<Student>();

        Course course = getCourseByName(courseName);

        if( course==null ){
            throw new CourseNotFoundByNameException(courseName);
        }

        String sql = "SELECT * FROM SemRegistration " +
                "INNER JOIN Student ON SemRegistration.StudentID = Student.StudentID WHERE SemRegistration.CourseID=?";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Student student = new Student();

                student.setUserID(rs.getInt("Student.StudentID"));
                student.setName(rs.getString("Student.Name"));
                student.setAddress(rs.getString("Student.Address"));
                student.setBranch(rs.getString("Student.Branch"));
                student.setDegree(rs.getString("Student.Degree"));
                student.setSemID(rs.getInt("SemID"));

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

        Student student = null;

        String sql = "Select StudentID, Name, Address, Branch, Degree FROM Student WHERE StudentID = (?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                student = new Student();

                student.setUserID(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setAddress(rs.getString("Address"));
                student.setBranch(rs.getString("Branch"));
                student.setDegree(rs.getString("Degree"));
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

        return student;
    }

    @Override
    public List<Course> getCoursesByProfessor(int profID) {

        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();

        String sql = "Select CourseID, Name, ProfID FROM Courses WHERE ProfID = (?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);

            ResultSet rs = stmt.executeQuery();

            while( rs.next() ) {
                semID = rs.getInt("SemID");
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

        return semID;
    }

    public Course getCourseByName(String courseName){

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        Course course = null;

        String sql = "SELECT CourseID, Name, ProfID FROM Courses WHERE Name=?";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseName);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                course = new Course();

                int cid = rs.getInt("CourseID");
                String name = rs.getString("Name");
                int pid = rs.getInt("ProfID");

                course.setCourseID(cid);
                course.setCourseName(name);
                course.setProfID(pid);
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

        return course;
    }

    public Course getCourseByID(int courseID){

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        Course course = null;

        String sql = "SELECT CourseID, Name, ProfID FROM Courses WHERE CourseID=?";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                course = new Course();

                int cid = rs.getInt("CourseID");
                String name = rs.getString("Name");
                int pid = rs.getInt("ProfID");

                course.setCourseID(cid);
                course.setCourseName(name);
                course.setProfID(pid);

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

        return course;
    }

    @Override
    public Professor getProfessorById(int profID) {

        Professor professor = null;

        PreparedStatement stmt = null;

        String sql = "SELECT ProfID, Name, Department, Designation FROM Professor WHERE ProfID = (?)";

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, profID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                professor = new Professor();

                int pid = rs.getInt("ProfID");
                String name = rs.getString("Name");
                String department = rs.getString("Department");
                String designation = rs.getString("Designation");

                professor.setUserID(pid);
                professor.setName(name);
                professor.setDepartment(department);
                professor.setDesignation(designation);
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

        return professor;
    }

}
