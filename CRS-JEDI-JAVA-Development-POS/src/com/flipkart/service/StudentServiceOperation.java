package com.flipkart.service;
import com.flipkart.bean.User;
import com.flipkart.bean.Course;
import com.flipkart.data.Data;

import java.util.*;
import com.flipkart.bean.Student;


public class StudentServiceOperation implements StudentInterface {

    int semID;
    Data studentsdata= new Data();

    public void register(String name, String studentID, String password){

    }

    public boolean login(String studentname, String password){
        List<Student> studentsList= studentsdata.students;
        for(Student s:studentsList)
        {
            if(s.getName()==studentname && s.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void semesterRegister(Student student){

        System.out.println("Select Semester to register:");
        for(Map.Entry m: Data.semCourseList.entrySet()){
            System.out.println(m.getKey());
        }

        Scanner sc = new Scanner(System.in);
        int semID = Integer.parseInt(sc.nextLine());

        student.setSemID(semID);
    }

    public List<Course> getCourses(int semID){
        return studentsdata.semCourseList.get(semID);
    }

    public void addCourse(){
        System.out.println("Select Course to Add:");
        for(Map.Entry m: Data.semCourseList.entrySet()){
            System.out.println(m.getKey());
        }

        Scanner sc = new Scanner(System.in);
        int semID = Integer.parseInt(sc.nextLine());
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
