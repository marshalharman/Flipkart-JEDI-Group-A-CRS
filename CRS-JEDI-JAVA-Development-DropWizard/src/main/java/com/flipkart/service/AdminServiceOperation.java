package com.flipkart.service;

import com.flipkart.bean.*;
import com.flipkart.dao.*;
import com.flipkart.exception.*;

import java.util.List;
import java.util.Scanner;


public class AdminServiceOperation implements AdminInterface {

    AdminDAOImpl adminDaoImpl=new AdminDAOImpl();
    Scanner sc=new Scanner(System.in);

    public void approveStudentRegistration(int studentID) throws StudentNotFoundForApprovalException,StudentAlreadyApproved {
        UserDAO dao = new UserDAOImpl();
        User u = dao.getUserByID(studentID);
        if(u==null)
        {
            throw new StudentNotFoundForApprovalException(studentID);
        }
        if(u.getIsApproved())
        {
            throw new StudentAlreadyApproved(studentID);
        }
        adminDaoImpl.approveStudent(studentID);
    }
    @Override
    public void addAdmin(Admin a) throws DuplicateUserException{
        UserDAOImpl userDAO = new UserDAOImpl();
        if(userDAO.getUserByID(a.getUserID())!=null)throw new DuplicateUserException(a.getUserID());
        userDAO.register(a.getUserID(), a.getName(), a.getPassword(), a.getRole(), true);
        adminDaoImpl.addAdmin(a.getUserID(), a.getName());
    }
    public void addProfessor(Professor p) throws DuplicateUserException, ProfessorNotAddedException {

        UserDAOImpl userDAO = new UserDAOImpl();
        if(userDAO.getUserByID(p.getUserID())!=null)throw new DuplicateUserException(p.getUserID());
        userDAO.register(p.getUserID(), p.getName(), p.getPassword(), p.getRole(), true);
        try {
            adminDaoImpl.addProfessor(p);
        }catch (ProfessorNotAddedException e)
        {
            throw new ProfessorNotAddedException((p.getUserID()));
        }
        System.out.println("Professor added successfully.");
    }

    public void addCourse(int courseID , String courseName, int semID) throws CourseAlreadyPresentException {
        Course c1=new Course();
        c1.setCourseID(courseID);
        c1.setCourseName(courseName);
        ProferssorDAO dao =new ProfessorDAOImpl();
        Course c = dao.getCourseByID(courseID);
        if(c!=null)throw new CourseAlreadyPresentException(courseID);
        adminDaoImpl.addCourse(c1, semID);

    }

    public void removeCourse(int semId , int courseId) throws CourseNotDeletedException, CourseNotFoundException {
        ProferssorDAO dao = new ProfessorDAOImpl();
        Course c = dao.getCourseByID(courseId);
        if(c==null)throw new CourseNotFoundException(courseId);
        try {
            adminDaoImpl.deleteCourse(courseId);
        }
        catch(CourseNotDeletedException e)
        {
            throw new CourseNotDeletedException(courseId);
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
