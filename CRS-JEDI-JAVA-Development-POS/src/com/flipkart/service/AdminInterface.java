package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface AdminInterface {

    public int login(String adminName, String password);
    public void approveStudentRegistration(int studentID);
    public void addAdmin(Admin a) throws SQLException, ClassNotFoundException;
    public void addProfessor(Professor p) throws SQLException, ClassNotFoundException;
    public void removeProfessor(String profName);
    public void addCourse(int semId , int courseId , String courseName) throws SQLException, ClassNotFoundException;
    public void removeCourse(int semId , int courseId) throws SQLException, ClassNotFoundException;
    public void generateGradeCard();

}
