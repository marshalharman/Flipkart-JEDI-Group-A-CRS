package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.sql.SQLException;


/**
 *
 * Interface for Admin Service Operations
 *
 */

public interface AdminInterface {

    /**
     * Method to approve a Student registration
     * @param studentID
     */
    public void approveStudentRegistration(int studentID);
    /**
     * Method to add admin to the DB
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
     */
    public void addProfessor(int userID,String userName,String password,String role,String name,String dept,String designation);
    /**
     * Method to add Course to Course Catalog
     * @param courseID : Course object storing details of a course
     * @param semID : Courses available in the catalog
     */
    public void addCourse(int courseID,String courseName, int semID);
    /**
     * Method to Remove Course from Course Catalog
     * @param semId
     * @param courseId : Courses available in the catalog
     */
    public void removeCourse(int semId , int courseId);
    /**
     * Method to generate report card of the student
     */
    public void generateGradeCard();

}
