package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.data.Data;

import com.flipkart.dao.AdminDAO;
import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.time.Period;
import java.util.*;


public class AdminServiceOperation implements AdminInterface {

    AdminDAOImpl adminDaoImpl=new AdminDAOImpl();
    Scanner sc=new Scanner(System.in);

    public void approveStudentRegistration(int studentID) throws StudentNotFoundForApprovalException {
        adminDaoImpl.approveStudent(studentID);
    }
    @Override
    public void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name){

        UserDAOImpl userDAO = new UserDAOImpl();

        userDAO.register(userID, userName, password, role, true);

        adminDaoImpl.addAdmin(userID, name);
    }
    public void addProfessor(int userID,String userName,String password,String role,String name,String dept,String designation) throws UserIdAlreadyInUseException, ProfessorNotAddedException {

        UserDAOImpl userDAO = new UserDAOImpl();

        userDAO.register(userID, userName, password, role, true);

        Professor professor = new Professor();
        professor.setUserID(userID);
        professor.setName(name);
        professor.setDepartment(dept);
        professor.setDesignation(designation);

        adminDaoImpl.addProfessor(professor);
    }

    public void addCourse(int courseID , String courseName, int semID)throws CourseAlreadyPresentException {

        Course c1=new Course();
        c1.setCourseID(courseID);
        c1.setCourseName(courseName);
        adminDaoImpl.addCourse(c1, semID);
        System.out.println(courseName + " added successfully.");

    }

    public void removeCourse(int semId , int courseId) throws CourseNotDeletedException, CourseNotFoundException {

        adminDaoImpl.deleteCourse(courseId);
        System.out.println("removed successfully.");
    }

    public void generateGradeCard() {
        adminDaoImpl.generateGradeCard();
    }
}
