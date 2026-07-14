/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.call_center_waiting_line_system;

import data_objects.CallsManager;
import java.util.Scanner;

/**
 *
 * @author dqhoa
 */
public class CallCenterWaitingLineSystem {
    public static void main(String[] args) {
        CallsManager call_manager = new CallsManager();
        Scanner scanner = new Scanner(System.in);
        call_manager.loadFile("data/customers.json");
        mainMenu(call_manager, scanner);
    }

    private static void mainMenu(CallsManager call_manager, Scanner scanner) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\n********** Main Menu ***********");
            System.out.println("1. Add customer");
            System.out.println("2. Display customer list (unsorted)");
            System.out.println("3. Display history");
            System.out.println("4. Process call");
            System.out.println("5. Process all calls");
            System.out.println("6. Remove call");
            System.out.println("7. Quit");
            System.out.print("Select option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    call_manager.addCustomer();
                    break;
                case "2":
                    call_manager.displayTable();
                    break;
                case "3":
                    call_manager.displayHistory();
                    break;
                case "4":
                    call_manager.processCall();
                    break;
                case "5":
                    while (!call_manager.isEmpty()) {
                        call_manager.processCall();
                    }
                    break;
                case "6":
                    call_manager.removeCustomer();
                    break;
                case "7":
                    quit = true;
                    break;
                default:
                    System.out.println("ERROR: Option invalid");
            }
        }
    }
}
