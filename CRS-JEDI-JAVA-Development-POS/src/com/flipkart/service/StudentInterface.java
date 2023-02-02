package com.flipkart.service;

public interface StudentInterface {
    public void register(String name, String studentID, String password);
    public boolean login(String studentname,String password);
    public void semesterRegister();
    public void getCourses();
    public void addCourse();
    public void dropCourse();
    public void getRegisteredCourses();
    public void payFees();
    public void viewGrades();

}
