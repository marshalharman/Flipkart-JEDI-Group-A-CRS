package com.flipkart.service;
import com.flipkart.data.Data;

import java.util.*;
import com.flipkart.bean.Student;


public class StudentServiceOperation implements StudentInterface {

    int semID;
    Scanner sc = new Scanner(System.in);
    public void register(){

        System.out.println("Enter your student ID: ");
        int studentId = sc.nextInt();

        System.out.println("Enter your name: ");
        String name = sc.next();

        System.out.println("Enter your address: ");
        String address = sc.next();

        System.out.println("Enter your username: ");
        String username = sc.next();

        System.out.println("Enter your password: ");
        String password = sc.next();

        System.out.println("Enter your branch: ");
        String branch = sc.next();

        Student student = new Student();
        student.setUserID(studentId);
        student.setName(name);
        student.setAddress(address);
        student.setUsername(username);
        student.setPassword(password);
        student.setBranch(branch);
        student.setRole("Student");

        System.out.println("Student registered successfully.");
    }

    public boolean login(String username, String password){
        Data sd= new Data();
        List<Student> studentsList= sd.students;
        for(Student s:studentsList)
        {
            if(s.getUsername()==username && s.getPassword().equals(password)) {
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
