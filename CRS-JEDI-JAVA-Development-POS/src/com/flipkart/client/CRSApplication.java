package com.flipkart.client;

import java.util.Scanner;

public class CRSApplication {

    public static void main(String args[]){

        System.out.println("Welcome to the CRS Application! Choose the operation given below!");
        System.out.println("1. Login \n2. Registration of the Student \n3. Update Password \n4. Exit");

        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice){
            case 1:{
                System.out.println("ENTER USERNAME");
                String userName = sc.nextLine();

                System.out.println("ENTER PASSWORD");
                String password = sc.nextLine();

                System.out.println("ENTER ROLE");
                String role = sc.nextLine();
                break;
            }
            case 2:{
                CRSStudentMenu crsStudentMenu = new CRSStudentMenu();
                System.out.println("ENTER STUDENT ID");
                break;
            }
            case 3:{
                System.out.println("Update password");
                break;
            }
            case 4:{
                System.out.println("Thank you!");
                break;
            }
        }
    }
}
