package com.flipkart.service;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminInterface {

    public int login(String adminName, String password);
    public void approveStudentRegistration(int studentID);
    public boolean addAdmin(Admin a);
    public boolean addProfessor(Professor p);
    public boolean removeProfessor(String profName);
    public void addCourse(int semId , int courseId , String courseName);
    public void removeCourse(int semId , int courseId);
    public void generateGradeCard();

}
