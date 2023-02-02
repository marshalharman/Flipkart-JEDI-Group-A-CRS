package com.flipkart.client;


import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;

import java.util.Scanner;

public class CRSAdminMenu {
    AdminServiceOperation service = new AdminServiceOperation();
    public void adminMenu()
    {

        while(true) {

            System.out.println("Admin Menu!");
            System.out.println("Choose one of the options");
            System.out.println("1. Approve Student Registration");
            System.out.println("2. Add Professor");
            System.out.println("3. Remove Professor");
            System.out.println("4. Add Courses");
            System.out.println("5. Delete Courses");
            System.out.println("6. Generate Grade Card");
            System.out.println("7. Logout");

            Scanner obj = new Scanner(System.in);
            int choice = Integer.parseInt(obj.nextLine());

            switch (choice) {
                case 1:
                    approvedStudentRegistration();
                    break;
                case 2:
                    addProfessor();
                    break;
                case 3:
                    removeProfessor();
                    break;
                case 4:
                    addCourses();
                    break;
                case 5:
                    deleteCourses();
                    break;
                case 6:
                    System.out.println("Generated Grade Card\n");
                    break;
                case 7:
                    System.out.println("Logged out\n");
                    break;
                default:
                    System.out.println("Choose in the given options\n");
            }

            if(choice == 7){break;}
        }
    }
	private void approvedStudentRegistration(){
        service.approveStudentRegistration();
    }
    private void addProfessor(){

    }
    private void removeProfessor(){

    }
    private void addCourses(){

    }
    private void deleteCourses(){

    }
    private void generateReportCard(){
    	
    }
}
