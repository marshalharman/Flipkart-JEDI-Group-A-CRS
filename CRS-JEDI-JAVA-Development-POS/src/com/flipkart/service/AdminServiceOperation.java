package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.data.Data;

import com.flipkart.dao.AdminDAO;
import com.flipkart.dao.AdminDAOImpl;

import java.sql.SQLException;
import java.time.Period;
import java.util.*;


public class AdminServiceOperation implements AdminInterface {

    AdminDAOImpl adminDaoImpl=new AdminDAOImpl();
    Scanner sc=new Scanner(System.in);

    public void approveStudentRegistration(int studentID) {
        adminDaoImpl.approveStudent(studentID);
    }
    @Override
    public void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name){

        UserDAOImpl userDAO = new UserDAOImpl();

        userDAO.register(userID, userName, password, role, true);

        adminDaoImpl.addAdmin(userID, name);
    }

    public void addProfessor(int userID,String userName,String password,String role,String name,String dept,String designation){

        UserDAOImpl userDAO = new UserDAOImpl();

        userDAO.register(userID, userName, password, role, true);

        Professor professor = new Professor();
        professor.setUserID(userID);
        professor.setName(name);
        professor.setDepartment(dept);
        professor.setDesignation(designation);

        adminDaoImpl.addProfessor(professor);;

        return ;
    }

    public void addCourse(int courseID , String courseName, int semID){

        Course c1=new Course();
        c1.setCourseID(courseID);
        c1.setCourseName(courseName);
        adminDaoImpl.addCourse(c1, semID);
        System.out.println(courseName + " added successfully.");

    }

    public void removeCourse(int semId , int courseId){

        adminDaoImpl.deleteCourse(courseId);
        System.out.println("removed successfully.");
    }

    public void generateGradeCard() {
        adminDaoImpl.generateGradeCard();
    }
}
