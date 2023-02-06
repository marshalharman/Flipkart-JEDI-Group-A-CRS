package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.data.Data;
import com.flipkart.service.ProfessorServiceOperation;
import java.util.Scanner;
public class CRSProfessorMenu {


    ProfessorServiceOperation service = new ProfessorServiceOperation();
    Scanner sc = new Scanner(System.in);
    public void professorMenu(int userID)
    {

        Professor professor = null;

        for(Professor p : Data.professors){
            if( p.getUserID() == userID ){
                professor = p;
                break;
            }
        }

        while(true) {

            System.out.println("\nProfessor Menu!");
            System.out.println("Choose one of the options");
            System.out.println("1. View Courses");
            System.out.println("2. Register Course for Teaching");
            System.out.println("3. Deregister Course");
            System.out.println("4. View enrolled students");
            System.out.println("5. Add grade");
            System.out.println("6. Logout");

            Scanner obj = new Scanner(System.in);
            int choice = Integer.parseInt(obj.nextLine());

            switch (choice) {
                case 1:
                    viewCourses(userID);
                    break;
                case 2:
                    registerCourse(userID);
                    break;
                case 3:
                    deRegisterCourse(userID);
                    break;
                case 4:
                    viewEnrolledStudent(userID);
                    break;
                case 5:
                    addGrades(userID);
                    break;
                case 6:
                    System.out.println("Logged Out");
                    break;
                default:
                    System.out.println("Please enter a valid input\n");
            }

            if(choice == 6 ){break;}

        }
    }
	private void viewCourses(int userID){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Semester ID : ");
        int semID = Integer.parseInt(sc.nextLine());

        service.viewCourse(semID);
    }
    private void registerCourse(int userID){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Semester ID : ");
        int semID = Integer.parseInt(sc.nextLine());

        service.viewCourse(semID);

        System.out.println("Enter Course Name : ");
        String courseName = sc.nextLine();

        service.registerCourse(userID, courseName, semID);

    }

    private void deRegisterCourse(int userID){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Semester ID : ");
        int semID = Integer.parseInt(sc.nextLine());

        service.viewCourse(semID);

        System.out.println("Enter Course Name : ");
        String courseName = sc.nextLine();

        service.deregisterCourse(userID, courseName, semID);

    }
    private void viewEnrolledStudent(int userID){
        System.out.println("Enter Semester ID : ");
        int semID = Integer.parseInt(sc.nextLine());
        service.viewCourse(semID);

        System.out.println("Enter course name : ");
        String courseName = sc.nextLine();

        service.viewEnrolledStudents(semID,courseName);
    }
    private void addGrades(int userID){
        System.out.println("Enter course ID : ");
        int courseID = Integer.parseInt(sc.nextLine());

        System.out.println("Enter student ID : ");
        int studentID = Integer.parseInt(sc.nextLine());

        System.out.println("Enter score : ");
        int score = Integer.parseInt(sc.nextLine());
        service.addGrade(userID,courseID,studentID,score);
    }
}
