package com.flipkart.client;

import com.flipkart.service.ProfessorServiceOperation;
import java.util.Scanner;
public class CRSProfessorMenu {


    ProfessorServiceOperation service = new ProfessorServiceOperation();
    public void professorMenu()
    {
        System.out.println("Professor Menu!");
        System.out.println("Choose one of the options");
        System.out.println("1. View Courses");
        System.out.println("2. Give course preference");
        //System.out.println("3. De-register from a course");
        System.out.println("4. View enrolled students");
        System.out.println("5. Add grade");

        Scanner obj = new Scanner(System.in);
        String choice;
        choice = obj.nextLine();
        switch(choice) {
            case "1":
                viewCourses();
            case "2":
                registerCourse();
            case "3":
                deRegisterCourse();
            case "4":
                viewEnrolledStudent();
            case "5":
                addGrades();
            default:
                System.out.println("Menu\n");
        }
    }
	private void viewCourses(){

    }
    private void registerCourse(){

    }
    private void deRegisterCourse(){

    }
    private void viewEnrolledStudent(){

    }
    private void addGrades(){

    }
}
