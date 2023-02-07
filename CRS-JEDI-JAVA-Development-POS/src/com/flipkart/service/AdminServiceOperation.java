package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.dao.AdminDAOImpl;

import java.util.*;

/**
 *
 * Implementations of Admin Operations
 *
 */

public class AdminServiceOperation implements AdminInterface {

    AdminDAOImpl adminDaoImpl=new AdminDAOImpl();
    Scanner sc=new Scanner(System.in);

    /**
     * Method to approve a Student registration
     * @param studentID
     */
    public void approveStudentRegistration(int studentID) {
        adminDaoImpl.approveStudent(studentID);
    }
    /**
     * Method to add admin to the DB
     * @param userID
     * @param userName
     * @param password
     * @param role
     * @param isApproved
     * @param name
     */
    @Override
    public void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name){

        UserDAOImpl userDAO = new UserDAOImpl();

        userDAO.register(userID, userName, password, role, true);

        adminDaoImpl.addAdmin(userID, name);
    }
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
    public void addProfessor(int userID,String userName,String password,String role,String name,String dept,String designation) {

        UserDAOImpl userDAO = new UserDAOImpl();

        userDAO.register(userID, userName, password, role, true);

        Professor professor = new Professor();
        professor.setUserID(userID);
        professor.setName(name);
        professor.setDepartment(dept);
        professor.setDesignation(designation);

        adminDaoImpl.addProfessor(professor);
    }
    /**
     * Method to add Course to Course Catalog
     * @param courseID : Course object storing details of a course
     * @param semID : Courses available in the catalog
     */
    public void addCourse(int courseID , String courseName, int semID){

        Course c1=new Course();
        c1.setCourseID(courseID);
        c1.setCourseName(courseName);
        adminDaoImpl.addCourse(c1, semID);
        System.out.println(courseName + " added successfully.");

    }
    /**
     * Method to Remove Course from Course Catalog
     * @param semId
     * @param courseId : Courses available in the catalog
     */
    public void removeCourse(int semId , int courseId){

        adminDaoImpl.deleteCourse(courseId);
        System.out.println("removed successfully.");
    }
    /**
     * Method to generate report card of the student
     */
    public void generateGradeCard() {
        adminDaoImpl.generateGradeCard();
    }
}
