package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.ConnectionConstant;
import com.flipkart.exception.*;
import com.flipkart.constant.SQLConstants;

import java.sql.*;
import java.util.*;
import java.util.concurrent.Semaphore;

public class AdminDAOImpl implements AdminDAO {



    public void addAdmin(int userID, String name){

        java.sql.Connection conn = null;
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
    public void deleteCourse(int courseID) throws CourseNotDeletedException, CourseNotFoundException {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;



        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);
            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql1 = SQLConstants.FIND_COURSE;
            stmt = conn.prepareStatement(sql1);
            stmt.setInt(1, courseID);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()==false)  {throw new CourseNotFoundException(courseID);}

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
        } catch (CourseNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
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

    public void addCourse(Course course, int semID) throws SemNotFoundException, CourseAlreadyPresentException {

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = "SELECT * from Catalog WHERE SemID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, semID);
            ResultSet res = stmt.executeQuery();
            //System.out.println(res.next());
            if(res.next()==false){
                throw new SemNotFoundException(semID);

            }

            sql = "SELECT * from Catalog WHERE CourseID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());
            res = stmt.executeQuery();
            //System.out.println(res.next());
            if(res.next()==true){
                throw new CourseAlreadyPresentException(course.getCourseID());
            }

            sql = "INSERT INTO Courses(CourseID, Name) VALUES (?, ?)";

            sql = SQLConstants.ADD_COURSE;

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());
            stmt.setString(2, course.getCourseName());
            stmt.executeUpdate();


            sql = SQLConstants.INSERT_INTO_CATALOG;
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, course.getCourseID());
            stmt.setInt(2, semID);
            stmt.executeUpdate();


        }catch (SemNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        catch(CourseAlreadyPresentException e){
            //Handle errors for JDBC
            System.out.println(e.getMessage());
            return;
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{;
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
        System.out.println(course.getCourseName() + " added successfully.");
    }

    @Override
    public List<Course> getCourses(int semID) {

        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        List<Course> courseList = new ArrayList<Course>();
        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            String sql = "SELECT Courses.CourseID, Courses.Name, Courses.ProfID " +
                    "FROM crs_database.Catalog INNER JOIN crs_database.Courses ON Catalog.CourseId = Courses.CourseID " +
                    "WHERE semID = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, semID);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Course course = new Course();
                course.setCourseID(rs.getInt("Courses.CourseID"));
                course.setCourseName(rs.getString("Courses.Name"));
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

    public void approveStudent(int studentId) throws StudentNotFoundForApprovalException {
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.APPROVE_STUDENT_BY_ID;

        try{
            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            int row = stmt.executeUpdate();
            if (row == 0) {
                System.out.println("Student with " + studentId + " not found.");
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

    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {
        java.sql.Connection conn = null;
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

        java.sql.Connection conn = null;
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
        java.sql.Connection conn = null;
        PreparedStatement stmt = null;

        String sql = SQLConstants.VIEW_UNAPPROVED_STUDENTS;

        List<Student> userList = new ArrayList<Student>();
        try {

            Class.forName(ConnectionConstant.JDBC_DRIVER);

            conn = DriverManager.getConnection(ConnectionConstant.DB_URL, ConnectionConstant.USER, ConnectionConstant.PASS);
            stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Student user = new Student();
                user.setUserID(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
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
        java.sql.Connection conn = null;
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
}
