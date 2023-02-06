package com.flipkart.dao;
import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import java.sql.*;
import java.util.*;
public class AdminDAOImpl implements AdminDAO{

    private PreparedStatement statement = null;
    Connection connection = null;
    public void deleteCourse(String courseCode) throws SQLException {
        statement = null;
        String sql = "delete from Course where courseCode = "+courseCode;
        statement = connection.prepareStatement(sql);

        statement.setString(1,courseCode);
        int row = statement.executeUpdate();
        System.out.println(row + " entries deleted");
 //       logger.info(row + " entries deleted.");
//        if(row == 0) {
//            logger.error(courseCode + " not in catalog!");
//            throw new CourseNotFoundException(courseCode);
//        }

        //logger.info("Course with courseCode: " + courseCode + " deleted.");
    }
    public void addCourse(Course course) throws SQLException {
        String sql = "insert into Course(courseCode, courseName, catalogId) values (?, ?, ?)";
        statement = connection.prepareStatement(sql);

        statement.setInt(1, course.getCourseID());
        statement.setString(2, course.getCourseName());

        statement.setInt(3, 1);
        int row = statement.executeUpdate();
        System.out.println(row + " entries added");

//        logger.info(row + " course added");
//        if(row == 0) {
//            logger.error("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
//            throw new CourseFoundException(course.getCourseCode());
//        }
//
//        logger.info("Course with courseCode: " + course.getCourseCode() + " is added to catalog.");
    }

    public void approveStudent(int studentId) throws SQLException {
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
    public void addUser(User user) throws SQLException {
        String sql = "nsert into User(userId, name, password, role, gender, address, country) values (?, ?, ?, ?, ?, ?, ?)";
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

    public void addProfessor(Professor professor) throws SQLException {
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
