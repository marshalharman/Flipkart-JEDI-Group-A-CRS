package com.flipkart.service;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminInterface {

    public int login(String adminName, String password);
    public void approveStudentRegistration();
    public boolean addProfessor(Professor p);
    public boolean removeProfessor(Professor p);
    public void addCourse();
    public void removeCourse();
    public void generateGradeCard();

}
