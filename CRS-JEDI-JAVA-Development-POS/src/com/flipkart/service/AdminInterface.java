package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * Interface for Admin Service Operations
 *
 */
public interface AdminInterface {
    /**
     * Method to approve student registration request
     * @param studentID
     * @throws StudentNotFoundForApprovalException
     */
    public void approveStudentRegistration(int studentID) throws StudentNotFoundForApprovalException;
    /**
     * Method to add admin user to the DB
     * @param userID
     * @param userName
     * @param password
     * @param role
     * @param isApproved
     * @param name
     */
    public void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name);
    /**
     * Method to add professor to the DB
     * @param userID
     * @param userName
     * @param password
     * @param role
     * @param name
     * @param dept
     * @param designation
     * @throws UserIdAlreadyInUseException
     * @throws ProfessorNotAddedException
     */
    public void addProfessor(int userID, String userName, String password, String role, String name, String dept, String designation) throws UserIdAlreadyInUseException, ProfessorNotAddedException;
    /**
     * Method to add course to the catalog
     * @param courseID
     * @param courseName
     * @param semID
     * @throws CourseAlreadyPresentException
     */
    public void addCourse(int courseID, String courseName, int semID) throws CourseAlreadyPresentException, SemNotFoundException;
    /**
     * Method to remove course from catalog
     * @param semId
     * @param courseId
     * @throws CourseNotDeletedException
     * @throws CourseNotFoundException
     */

    /**
     * Method to get all courses in a given semester
     * @param semID
     * @return List of all courses in a given semester
     */
    public List<Course> getCourses(int semID);

    public void removeCourse(int semId, int courseId) throws CourseNotDeletedException, CourseNotFoundException;
    /**
     * Method to generate grade card for a student
     */
    public void generateGradeCard();
    /**
     * Method to view list of unapproved students
     * @return List of unapproved students
     */
    public List<Student> viewUnapprovedStudents();
    /**
     * Method to approve all unapproved students
     */
    public void approveAllStudents();
}
