package com.flipkart.client;

import com.flipkart.constant.Role;
import com.flipkart.exception.PrimaryKeyException;
import com.flipkart.exception.*;
import com.flipkart.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CRSApplication {

    public static void main(String args[]) throws CourseNotDeletedException, CourseNotFoundException, CourseAlreadyPresentException, StudentNotFoundForApprovalException, UserIdAlreadyInUseException, ProfessorNotAddedException, CourseNotFoundByNameException {
        while(true){

            System.out.println("\n\nWelcome to the CRS Application! Choose the operation given below!\n");

            System.out.println("****************************************************");
            System.out.println("********************* MAIN MENU ********************");
            System.out.println("****************************************************");
            System.out.println();
            System.out.println("1. Login \n2. Registration of the Student \n3. Update Password \n4. Exit");

            Scanner sc = new Scanner(System.in);

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input should be numerical!");
            }

            switch (choice) {
                case 1: {
                    String password, role;
                    int userID;
                    while(true){
                        System.out.println("ENTER USERID");
                        try{
                            userID = Integer.parseInt(sc.nextLine());
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please provide ID in proper format\n");
                            break;
                        }
                        System.out.println("ENTER PASSWORD");
                        password = sc.nextLine();

                        System.out.println("ENTER ROLE");
                        role = sc.nextLine();

                        UserInterface userServiceOperation = new UserServiceOperation();
                        boolean verified = userServiceOperation.verifyCredentials(userID, password, role);

                        LocalDateTime localDateTime = LocalDateTime.now();
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formatDateTime = localDateTime.format(format);


                        if (role.equalsIgnoreCase(Role.STUDENT)) {
                            if(verified) {
                                System.out.println("Welcome to the Course Registration System! Login Time: " + formatDateTime);
                                CRSStudentMenu crsStudentMenu = new CRSStudentMenu();
                                crsStudentMenu.studentMenu(userID);
                                break;
                            }
                            System.out.println("Wrong Credentials\n");
                            break;
                        } else
                            if (role.equalsIgnoreCase(Role.PROFESSOR)) {
                                if( verified ) {
                                    System.out.println("Welcome to the Course Registration System! Login Time: " + formatDateTime);
                                    CRSProfessorMenu crsProfessorMenu = new CRSProfessorMenu();
                                    crsProfessorMenu.professorMenu(userID);
                                    break;
                                }
                                System.out.println("Wrong Credentials\n");
                                break;
                        } else
                            if (role.equalsIgnoreCase(Role.ADMIN)) {
                                if(verified) {
                                    System.out.println("Welcome to the Course Registration System! Login Time: " + formatDateTime);
                                    CRSAdminMenu crsAdminMenu = new CRSAdminMenu();
                                    crsAdminMenu.adminMenu(userID);
                                    break;
                                }
                                System.out.println("Wrong Credentials\n");
                                break;
                        } else{
                                System.out.println("Please Provide valid role\n");
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
                        break;
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
                    System.out.println("Enter your UserID : ");
                    int userId;
                    try{
                        userId= Integer.parseInt(sc.nextLine());
                    }catch(Exception e)
                    {
                        System.out.println("Please provide ID\n");
                        break;
                    }
                    System.out.println("Enter your password: ");
                    String password = sc.nextLine();
                    UserInterface userServiceOperation = new UserServiceOperation();
                    userServiceOperation.updatePassword(userId,password);
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
