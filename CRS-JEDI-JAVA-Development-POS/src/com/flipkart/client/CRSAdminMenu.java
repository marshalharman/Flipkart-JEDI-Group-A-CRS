package com.flipkart.client;


import com.flipkart.service.AdminServiceOperation;

import java.util.Scanner;

public class CRSAdminMenu {
    AdminServiceOperation service = new AdminServiceOperation();
    public void adminMenu()
    {
        System.out.println("Admin Menu!\n");
        System.out.println("Choose one of the options\n");
        System.out.println("1. Approve Student Registration\n");
        System.out.println("2. Add Professor\n");
        System.out.println("3. Remove Professor\n");
        System.out.println("4. Add Courses\n");
        System.out.println("5. Delete Courses\n");
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
