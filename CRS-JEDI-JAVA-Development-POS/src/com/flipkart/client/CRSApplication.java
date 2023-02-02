package com.flipkart.client;

import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserServiceOperation;

import java.util.Scanner;

public class CRSApplication {

    public static void main(String args[]){

        while(true){

            System.out.println("Welcome to the CRS Application! Choose the operation given below!");
            System.out.println("1. Login \n2. Registration of the Student \n3. Update Password \n4. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: {
                    String userName, password, role;
                    while(true){
                        System.out.println("ENTER USERNAME");
                        userName = sc.nextLine();

                        System.out.println("ENTER PASSWORD");
                        password = sc.nextLine();

                        System.out.println("ENTER ROLE");
                        role = sc.nextLine();

                        UserInterface userServiceOperation = new UserServiceOperation();
                        boolean verified = userServiceOperation.verifyCredentials(userName, password);

                        if(!verified){
                            System.out.println("Wrong Username/Password. Try again!");
                        }
                        else {
                            break;
                        }
                    }

                    if (role.equalsIgnoreCase("student")) {
                        CRSStudentMenu crsStudentMenu = new CRSStudentMenu();
                        crsStudentMenu.studentMenu(101);
                    } else if (role.equalsIgnoreCase("professor")) {
                        CRSProfessorMenu crsProfessorMenu = new CRSProfessorMenu();
                        crsProfessorMenu.professorMenu();
                    } else if (role.equalsIgnoreCase("admin")) {
                        CRSAdminMenu crsAdminMenu = new CRSAdminMenu();
                        crsAdminMenu.adminMenu();

                    }
                    break;
                }
                case 2: {
                    System.out.println("ENTER Name");
                    String name = sc.nextLine();

                    System.out.println("ENTER STUDENT ID");
                    String studentID = sc.nextLine();

                    System.out.println("ENTER PASSWORD");
                    String password = sc.nextLine();

                    StudentServiceOperation studentServiceOperation = new StudentServiceOperation();
                    studentServiceOperation.register();

                    System.out.println("Registration request sent!");
                    break;
                }
                case 3: {
                    System.out.println("Update Password");
                    break;
                }
                case 4: {
                    System.out.println("Thank you!");
                    break;
                }
                default:
                    System.out.println("Please give a valid input\n");
            }

            if(choice == 4){break;}

        }


    }
}
