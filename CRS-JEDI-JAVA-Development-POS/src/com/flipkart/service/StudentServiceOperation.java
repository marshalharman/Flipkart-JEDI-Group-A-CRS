package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.data.StudentData;

import java.util.List;

public class StudentServiceOperation implements StudentInterface {

    public void register(String name, String studentID, String password){

    }

    public boolean login(int studentID, String password){
        StudentData sd= new StudentData();
        List<Student> studentsList= sd.studentsList;
        for(Student s:studentsList)
        {
            if(s.getUserID()==studentID && s.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void semesterRegister(){

    }

    public void getCourses(){

    }

    public void addCourse(){

    }

    public void dropCourse(){

    }

    public void getRegisteredCourses(){

    }

    public void payFees(){

    }

    public void viewGrades(){

    }


}
