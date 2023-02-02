package com.flipkart.client;


import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.data.Data;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminServiceOperation;

import java.util.Scanner;

public class CRSAdminMenu {
    AdminInterface service = new AdminServiceOperation();
    public void adminMenu(int id)
    {

        while(true) {

            System.out.println("\nAdmin Menu!");
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
                    System.out.println("Give Prof ID,Prof name, Password\n");
                    Professor p=new Professor();
                    int userID = Integer.parseInt(obj.nextLine());
                    p.setUserID(userID);
                    String userName =obj.nextLine();
                    p.setName(userName);
                    String password = obj.nextLine();
                    p.setPassword(password);
                    addProfessor(p);
                    break;
                case 3:
                    System.out.println("Give Prof name: \n");
                    String profName = obj.nextLine();
                    removeProfessor(profName);
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
    private void addProfessor(Professor p){
        service.addProfessor(p);
    }
    private void removeProfessor(String profName){
        service.removeProfessor(profName);
    }
    private void addCourses(){
        service.addCourse();
    }
    private void deleteCourses(){
        service.removeCourse();
    }
    private void generateReportCard(){
    	service.generateGradeCard();
    }
}
