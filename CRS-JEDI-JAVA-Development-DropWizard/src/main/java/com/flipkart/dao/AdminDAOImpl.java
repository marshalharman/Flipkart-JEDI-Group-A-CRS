package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.ConnectionConstant;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.*;

import com.flipkart.exception.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AdminDAOImpl implements AdminDAO {



    public void addAdmin(int userID, String name){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.ADD_ADMIN;

        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);
            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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
    public void deleteCourse(int courseID) throws CourseNotDeletedException {
        Connection conn = null;
        PreparedStatement stmt = null;


        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);
            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql1 = SQLConstants.FIND_COURSE;
            stmt = conn.prepareStatement(sql1);
            stmt.setInt(1, courseID);
            ResultSet rs = stmt.executeQuery();

            String sql = SQLConstants.DELETE_FROM_CATALOG;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();

            sql = SQLConstants.DELETE_FROM_SEM_REGISTRATION;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();



            sql = SQLConstants.DELETE_FROM_COURSES;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, courseID);
            stmt.executeUpdate();

            System.out.println("Course removed successfully.");

        } catch(SQLException se){
            //Handle errors for JDBC
            throw new CourseNotDeletedException(courseID);
        } catch(Exception e){
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
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = SQLConstants.ADD_COURSE;

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());
            stmt.executeUpdate();


            sql = SQLConstants.INSERT_INTO_CATALOG;
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

    public void approveStudent(int studentId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.APPROVE_STUDENT_BY_ID;
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            int row = stmt.executeUpdate();


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

    public void addProfessor(Professor professor) throws ProfessorNotAddedException {
        Connection conn = null;
        PreparedStatement stmt = null;


        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = SQLConstants.ADD_PROFESSOR;
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, professor.getUserID());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getDepartment());
            stmt.setString(4, professor.getDesignation());
            int row = stmt.executeUpdate();
            if (row == 0) {
                System.out.println("Professor not added.");
                throw new ProfessorNotAddedException(professor.getUserID());

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

    public void generateGradeCard(){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.GENERATE_GRADE_CARD;

        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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

    public List<Student> viewUnapprovedStudents() {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.VIEW_UNAPPROVED_STUDENTS;

        List<Student> userList = new ArrayList<Student>();
        try {

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);
            stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Student user = getStudentByID(resultSet.getInt("UserID"));
                userList.add(user);
            }

        }
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end t
        return userList;
    }

    public void approveAllStudents() {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.APPROVE_ALL_STUDENTS;

        try {
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1,studentId);
            int row = stmt.executeUpdate();
            if (row == 0) {
                System.out.println("No student to approve.");
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end tr
    }

    public Student getStudentByID(int studentID){

        PreparedStatement stmt = null;
        Connection conn = null;

        Student student = null;

        String sql = SQLConstants.GET_STUDENT_BY_ID;

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                student = new Student();

                student.setUserID(rs.getInt("StudentID"));
                student.setName(rs.getString("Name"));
                student.setAddress(rs.getString("Address"));
                student.setBranch(rs.getString("Branch"));
                student.setDegree(rs.getString("Degree"));
                student.setSemID(rs.getInt("SemID"));
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
}
