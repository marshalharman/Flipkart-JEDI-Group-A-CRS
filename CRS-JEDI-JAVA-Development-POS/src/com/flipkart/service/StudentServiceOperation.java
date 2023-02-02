package com.flipkart.service;
import com.flipkart.data.Data;

import java.util.*;
import com.flipkart.bean.Student;


public class StudentServiceOperation implements StudentInterface {

    int semID;
    public void register(String name, String studentID, String password){

    }

    public boolean login(String studentname, String password){
        Data sd= new Data();
        List<Student> studentsList= sd.students;
        for(Student s:studentsList)
        {
            if(s.getName()==studentname && s.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void semesterRegister(){

        System.out.println("Select Semester to register:");
        for(Map.Entry m: Data.semCourseList.entrySet()){
            System.out.println(m.getKey());
        }

        Scanner sc = new Scanner(System.in);
        semID = Integer.parseInt(sc.nextLine());
    }

    public void getCourses(){

    }

    public void addCourse(){
        System.out.println("Select Course to Add:");
        for(Map.Entry m: Data.semCourseList.entrySet()){
            System.out.println(m.getKey());
        }

        Scanner sc = new Scanner(System.in);
        semID = Integer.parseInt(sc.nextLine());
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
