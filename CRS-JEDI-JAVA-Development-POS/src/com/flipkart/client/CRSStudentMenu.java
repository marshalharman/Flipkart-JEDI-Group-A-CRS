package com.flipkart.client;

import com.flipkart.service.StudentServiceOperation;

import java.util.Scanner;

public class CRSStudentMenu {

    StudentServiceOperation service = new StudentServiceOperation();
    private void register(){

    }

    private void addCourse(){

    }
    private void deleteCourse(){

    }
    private void viewCourses(){

    }
    private void viewGrades(){

    }
    private void payFees() {

    }
    public void createMenu(int id){
        System.out.println("menu is created");
        System.out.println("Student Menu!\n");
        System.out.println("Choose one of the options\n");
        System.out.println("1. Course Registration\n");
        System.out.println("2. Add Course\n");
        System.out.println("3. Drop Course\n");
        System.out.println("4. View Course\n");
        System.out.println("5. View grades\n");
        System.out.println("6. Make Payment\n");
        Scanner obj = new Scanner(System.in);
        String choice;
        choice = obj.nextLine();
        switch(choice) {
            case "1":
                register();
            case "2":
                addCourse();
            case "3":
                deleteCourse();
            case "4":
                viewCourses();
            case "5":
                viewGrades();
            case "6":
                payFees();
            default:
                System.out.println("Menu\n");
        }
    }
}
