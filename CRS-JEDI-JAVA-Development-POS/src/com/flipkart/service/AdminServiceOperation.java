package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.UserDAOImpl;

import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.exception.*;

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
        System.out.println("Professor added successfully.");
    }

    public void addCourse(int courseID , String courseName, int semID) throws CourseAlreadyPresentException, SemNotFoundException {
        Course c1=new Course();
        c1.setCourseID(courseID);
        c1.setCourseName(courseName);
        try {
            adminDaoImpl.addCourse(c1, semID);
        }
        catch(CourseAlreadyPresentException e)
        {
            System.out.println(e.getMessage());
            return;
        }
//        catch (SemNotFoundException e) {
//            System.out.println(e.getMessage());
//            return;
//        }
//        System.out.println(courseName + " added successfully.");
    }

    @Override
    public List<Course> getCourses(int semID) {
        return adminDaoImpl.getCourses(semID);
    }

    public void removeCourse(int semId , int courseId) throws CourseNotDeletedException, CourseNotFoundException {
        try {
            adminDaoImpl.deleteCourse(courseId);
        }

        catch(CourseNotDeletedException e)
        {
            System.out.println(e.getMessage());
            return;
        }

    }

    public void generateGradeCard() {
        adminDaoImpl.generateGradeCard();
    }

    public List<Student> viewUnapprovedStudents(){
        return adminDaoImpl.viewUnapprovedStudents();
    }

    public void approveAllStudents(){
        adminDaoImpl.approveAllStudents();
    }
}
