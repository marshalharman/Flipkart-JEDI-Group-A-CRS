package com.flipkart.dao;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.sql.*;
import java.util.*;
public class AdminDAOImpl implements AdminDAO{

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs-database";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "17102080";

    private PreparedStatement statement = null;
    public void deleteCourse(Integer courseId) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL,USER,PASS);
        String sql = "delete from Course where courseId = "+courseId;

        statement = connection.prepareStatement(sql);

        statement.setInt(1,courseId);
        int row = statement.executeUpdate();
        System.out.println(row + " entries deleted");


 //       logger.info(row + " entries deleted.");
//        if(row == 0) {
//            logger.error(courseId + " not in catalog!");
//            throw new CourseNotFoundException(courseId);
//        }

        //logger.info("Course with courseId: " + courseId + " deleted.");
    }
    public void addCourse(Course course) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL,USER,PASS);

        String sql = "insert into Course(courseId, courseName, catalogId) values (?, ?, ?)";


        statement.setInt(1, course.getCourseID());
        statement.setString(2, course.getCourseName());

        statement.setInt(3, course.getProfID());
        int row = statement.executeUpdate();
        System.out.println(row + " entries added");

//        logger.info(row + " course added");
//        if(row == 0) {
//            logger.error("Course with courseId: " + course.getcourseId() + "not added to catalog.");
//            throw new CourseFoundException(course.getcourseId());
//        }
//
//        logger.info("Course with courseId: " + course.getcourseId() + " is added to catalog.");
    }

    public void approveStudent(int studentId) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL,USER,PASS);

        String sql="update Student set isApproved = 1 where studentId = ?";
        statement=connection.prepareStatement(sql);
        statement.setInt(1,studentId);
        int row = statement.executeUpdate();
        System.out.println(row + " student approved.");
//        logger.info(row + " student approved.");
//        if(row == 0) {
//            //logger.error("Student with studentId: " + studentId + " not found.");
//            throw new StudentNotFoundForApprovalException(studentId);
//        }

        //logger.info("Student with studentId: " + studentId + " approved by admin.");
    }
    @Override
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL,USER,PASS);

        String sql = "insert into User(userId, name, password, role, gender, address, country) values (?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);

        statement.setInt(1, user.getUserID());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getRole().toString());
        //statement.setBoolean(5, user.isApproved());
        int row = statement.executeUpdate();
        System.out.println(row + " user added.");
//        logger.info(row + " user added.");
//        if(row == 0) {
//            logger.error("User with userId: " + user.getUserId() + " not added.");
//            throw new UserNotAddedException(user.getUserId());
//        }
//
//        logger.info("User with userId: " + user.getUserId() + " added.");
    }

    public void addProfessor(Professor professor) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;

        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        connection = DriverManager.getConnection(DB_URL,USER,PASS);

        this.addUser(professor);
        String sql = "insert into Professor(userId, department, designation) values (?, ?, ?)";
        statement = connection.prepareStatement(sql);

        statement.setInt(1, professor.getUserID());
        statement.setString(2, professor.getName());
        statement.setString(3, professor.getDepartment());
        statement.setString(4, professor.getDesignation());
        int row = statement.executeUpdate();

        System.out.println(row + " professor added. ");

//        logger.info(row + " professor added.");
//        if(row == 0) {
//            logger.error("Professor with professorId: " + professor.getUserId() + " not added.");
//            throw new ProfessorNotAddedException(professor.getUserId());
//        }
//
//        logger.info("Professor with professorId: " + professor.getUserId() + " added.");
    }


}
