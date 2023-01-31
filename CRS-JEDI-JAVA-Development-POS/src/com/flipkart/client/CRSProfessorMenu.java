package com.flipkart.client;

import com.flipkart.service.ProfessorServiceOperation;
import java.util.Scanner;
public class CRSProfessorMenu {


    ProfessorServiceOperation service = new ProfessorServiceOperation();
    public void ProfessorMenu()
    {
        System.out.println("Professor Menu!\n");
        System.out.println("Choose one of the options\n");
        System.out.println("1. View Courses\n");
        System.out.println("2. Register for a course\n");
        System.out.println("3. De-register from a course\n");
        System.out.println("4. View enrolled students\n");
        System.out.println("5. Add course to the catalogue\n");

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
