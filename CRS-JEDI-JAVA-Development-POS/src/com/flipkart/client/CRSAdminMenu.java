package com.flipkart.client;


import com.flipkart.service.AdminServiceOperation;

import java.util.Scanner;

public class CRSAdminMenu {
    AdminServiceOperation service = new AdminServiceOperation();
    public void adminMenu()
    {
        System.out.println("Admin Menu!");
        System.out.println("Choose one of the options");
        System.out.println("1. Approve Student Registration");
        System.out.println("2. Add Professor");
        System.out.println("3. Remove Professor");
        System.out.println("4. Add Courses");
        System.out.println("5. Delete Courses");
        System.out.println("6. Logout");

        Scanner obj = new Scanner(System.in);
        String choice;
        choice = obj.nextLine();
        switch(choice) {
            case "1":
                approvedStudentRegistration();
            case "2":
                addProfessor();
            case "3":
                removeProfessor();
            case "4":
                addCourses();
            case "5":
                deleteCourses();
            case "6":
                System.out.println("Logged out\n");
            default:
                System.out.println("Menu\n");
        }
    }
	private void approvedStudentRegistration(){
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
