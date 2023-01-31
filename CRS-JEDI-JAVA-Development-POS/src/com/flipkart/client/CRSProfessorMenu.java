package com.flipkart.client;

import com.flipkart.service.ProfessorServiceOperation;
import java.util.Scanner;
public class CRSProfessorMenu {


    ProfessorServiceOperation service = new ProfessorServiceOperation();
    public void professorMenu()
    {

        while(true) {

            System.out.println("Professor Menu!");
            System.out.println("Choose one of the options");
            System.out.println("1. View Courses");
            System.out.println("2. Give course preference");
            System.out.println("3. View enrolled students");
            System.out.println("4. Add grade");
            System.out.println("5. Logout");

            Scanner obj = new Scanner(System.in);
            int choice = Integer.parseInt(obj.nextLine());

            switch (choice) {
                case 1:
                    viewCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    viewEnrolledStudent();
                    break;
                case 4:
                    addGrades();
                    break;
                case 5:
                    System.out.println("Logged Out");
                    break;
                default:
                    System.out.println("Please enter a valid input\n");
            }

            if(choice == 5 ){break;}

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
