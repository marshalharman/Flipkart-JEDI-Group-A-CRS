package com.flipkart.client;

import com.flipkart.exception.PrimaryKeyException;
import com.flipkart.service.*;

import java.util.Scanner;

public class CRSApplication {

    public static void main(String args[]){
        while(true){

            System.out.println("\n\nWelcome to the CRS Application! Choose the operation given below!");
            System.out.println("1. Login \n2. Registration of the Student \n3. Update Password \n4. Exit");

            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: {
                    String userName, password, role;
                    int userID;
                    while(true){
                        System.out.println("ENTER USERID");
                        userID = Integer.parseInt(sc.nextLine());

                        System.out.println("ENTER PASSWORD");
                        password = sc.nextLine();

                        System.out.println("ENTER ROLE");
                        role = sc.nextLine();

                        UserInterface userServiceOperation = new UserServiceOperation();
                        boolean verified = userServiceOperation.verifyCredentials(userID, password, role);


                        if (role.equalsIgnoreCase("student")) {
                            if(verified) {
                                CRSStudentMenu crsStudentMenu = new CRSStudentMenu();
                                crsStudentMenu.studentMenu(userID);
                                break;
                            }
                            System.out.println("Wrong Credentials\n");
                            break;
                        } else
                            if (role.equalsIgnoreCase("professor")) {
                                if( verified ) {
                                    CRSProfessorMenu crsProfessorMenu = new CRSProfessorMenu();
                                    crsProfessorMenu.professorMenu(userID);
                                    break;
                                }
                                System.out.println("Wrong Credentials\n");
                                break;
                        } else
                            if (role.equalsIgnoreCase("admin")) {
                                if(verified) {
                                    CRSAdminMenu crsAdminMenu = new CRSAdminMenu();
                                    crsAdminMenu.adminMenu(userID);
                                    break;
                                }
                                System.out.println("Wrong Credentials\n");
                                break;
                        }
                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter your student ID: ");

                    int studentId;
                    try{
                    studentId= Integer.parseInt(sc.nextLine());
                    }catch(Exception e)
                    {
                        System.out.println("Please provide ID\n");
                        return;
                    }


                    System.out.println("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.println("Enter your address: ");
                    String address = sc.nextLine();

                    System.out.println("Enter your username: ");
                    String username = sc.nextLine();

                    System.out.println("Enter your password: ");
                    String password = sc.nextLine();

                    System.out.println("Enter your branch: ");
                    String branch = sc.nextLine();

                    System.out.println(("Enter your degree: "));
                    String degree = sc.nextLine();

                    StudentServiceOperation studentServiceOperation = new StudentServiceOperation();
                    studentServiceOperation.register(studentId,name,address,username, password, branch, degree);
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
