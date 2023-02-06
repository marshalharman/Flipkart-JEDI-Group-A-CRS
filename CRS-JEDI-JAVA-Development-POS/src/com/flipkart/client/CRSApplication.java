package com.flipkart.client;

import com.flipkart.service.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CRSApplication {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String args[]){

        Connection conn = null;
        PreparedStatement stmt = null;

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
//                        userName = sc.nextLine();

                        System.out.println("ENTER PASSWORD");
                        password = sc.nextLine();

                        System.out.println("ENTER ROLE");
                        role = sc.nextLine();

                        UserInterface userServiceOperation = new UserServiceOperation();
                        boolean verified = userServiceOperation.verifyCredentials(userID, password);


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
                    StudentServiceOperation studentServiceOperation = new StudentServiceOperation();
                    studentServiceOperation.register();
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
