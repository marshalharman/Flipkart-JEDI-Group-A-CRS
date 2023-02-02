package com.flipkart.service;

import com.flipkart.bean.Student;

public interface StudentInterface {
    public void register(String name, String studentID, String password);
    public boolean login(int studentID,String password);
    public void semesterRegister(Student student);
    public void getCourses();
    public void addCourse();
    public void dropCourse();
    public void getRegisteredCourses();
    public void payFees();
    public void viewGrades();

}
