package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.ConnectionConstant;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLConstants;
import com.flipkart.exception.DuplicateUserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{

    UserDAO userDAO = new UserDAOImpl();

    public void register(int studentID, String name, String address, String username, String password, String branch, String degree) throws DuplicateUserException {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        User u=userDAO.getUserByID(studentID);
        if(u!=null)
        {
            throw new DuplicateUserException(studentID);
        }

        String role = Role.STUDENT;
        String sql1 = SQLConstants.REGISTER_USER;

        String sql2 = SQLConstants.REGISTER_STUDENT;

        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        List<Integer> semList = new ArrayList<Integer>();
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql1 = SQLConstants.GET_SEMESTER_LIST;
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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.SET_SEM_ID;

        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = SQLConstants.GET_COURSES;

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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        HashMap<Integer, Integer> courseEnrollmentCount = new HashMap<>();
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = SQLConstants.GET_COURSE_ENROLLMENT_COUNT;
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int courseID = rs.getInt("CourseID");
                int count = rs.getInt("Frequency");
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

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);
            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = SQLConstants.REGISTER_COURSES;
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

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.DROP_COURSE;

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;


        List<Course> registeredCourse = new ArrayList<>();

        String sql = SQLConstants.GET_REGISTERED_COURSE;
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentID);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                Course regCourse = new Course();

                regCourse.setCourseID(rs.getInt("Courses.CourseID"));
                regCourse.setCourseName(rs.getString("Courses.Name"));
                regCourse.setProfID(rs.getInt("Courses.ProfID"));

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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        HashMap<Course, String> GradesInCourses = new HashMap<>() ;

        String sql1 = SQLConstants.CHECK_GRADES_ENABLED;


        String sql = SQLConstants.VIEW_GRADES;

        try{

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

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
        java.sql.Connection conn = null;

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
