package com.flipkart.service;

import com.flipkart.data.Data;

import java.util.Map;
import java.util.Scanner;

public class StudentServiceOperation implements StudentInterface {

    int semID;
    public void register(String name, String studentID, String password){

    }

    public void login(){

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
