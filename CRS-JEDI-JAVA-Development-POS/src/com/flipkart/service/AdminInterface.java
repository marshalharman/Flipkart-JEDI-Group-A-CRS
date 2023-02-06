package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface AdminInterface {

    public void approveStudentRegistration(int studentID);
    public void addAdmin(int userID, String userName, String password, String role, boolean isApproved, String name);
    public void addProfessor(int userID,String userName,String password,String role,String name,String dept,String designation);
    public void addCourse(int courseID,String courseName, int semID);
    public void removeCourse(int semId , int courseId);
    public void generateGradeCard();

}
