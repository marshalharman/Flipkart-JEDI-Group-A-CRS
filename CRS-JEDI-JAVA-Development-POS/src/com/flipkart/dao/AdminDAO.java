package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;
/**
 *
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDAO {
    /**
     * Method to add admin using SQL commands
     * @param userID
     * @param name
     */
    public void addAdmin(int userID, String name);
    /**
     * Method to delete courses using SQL commands
     * @param courseID
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void deleteCourse(int courseID) throws CourseNotFoundException, CourseNotDeletedException;
    /**
     * Method to add course using SQL commands
     * @param course
     * @param semID
     * @throws CourseAlreadyPresentException
     */
    public void addCourse(Course course, int semID) throws CourseAlreadyPresentException, SemNotFoundException;
    /**
     * Method to approve student using SQL commands
     * @param studentID
     * @throws StudentNotFoundForApprovalException
     */
    public void approveStudent(int studentID) throws StudentNotFoundForApprovalException;
    /**
     * Method to add professor using SQL commands
     * @param professor
     * @throws ProfessorNotAddedException
     * @throws UserIdAlreadyInUseException
     */
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

    /**
     * Method to get all courses in a given semester
     * @param semID
     * @return List of all courses in a given semester
     */
    public List<Course> getCourses(int semID);

    /**
     * Method to generate grade card using SQL commands
     */

    public void generateGradeCard();
    /**
     * Method to view approved students using SQL commands
     * @return list of unapproved students
     */
    public List<Student> viewUnapprovedStudents();
    /**
     * Method to approve all unapproved students
     */
    public void approveAllStudents();
}
