package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDAOImpl;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;

import java.util.List;
import java.util.Scanner;


public class AdminServiceOperation implements AdminInterface {

    AdminDAOImpl adminDaoImpl=new AdminDAOImpl();
    Scanner sc=new Scanner(System.in);

    public void approveStudentRegistration(int studentID) throws StudentNotFoundForApprovalException {
        adminDaoImpl.approveStudent(studentID);
    }
    @Override
    public void addAdmin(Admin a) throws DuplicateUserException{
        UserDAOImpl userDAO = new UserDAOImpl();
        if(userDAO.getUserByID(a.getUserID())==null)throw new DuplicateUserException(a.getUserID());
        userDAO.register(a.getUserID(), a.getName(), a.getPassword(), a.getRole(), true);
        adminDaoImpl.addAdmin(a.getUserID(), a.getName());
    }
    public void addProfessor(Professor p) throws DuplicateUserException, ProfessorNotAddedException {

        UserDAOImpl userDAO = new UserDAOImpl();
        if(userDAO.getUserByID(p.getUserID())==null)throw new DuplicateUserException(p.getUserID());
        userDAO.register(p.getUserID(), p.getName(), p.getPassword(), p.getRole(), true);
        try {
            adminDaoImpl.addProfessor(p);
        }catch (ProfessorNotAddedException e)
        {
            throw new ProfessorNotAddedException((p.getUserID()));
        }
        System.out.println("Professor added successfully.");
    }

    public void addCourse(int courseID , String courseName, int semID) {
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
        System.out.println(courseName + " added successfully.");

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
