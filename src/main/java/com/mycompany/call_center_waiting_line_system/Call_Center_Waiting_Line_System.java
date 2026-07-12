/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.call_center_waiting_line_system;
import data_objects.*;

/**
 *
 * @author dqhoa
 */
public class Call_Center_Waiting_Line_System {

    public static void main(String[] args) {
        CallsManager call_manager = new CallsManager();
        call_manager.addCustomer(new Customer("asuidhasiudgasd", "187341287", 2, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("oihgsoibiasdfif", "98657279634", 6, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("fiuvgiuia", "1374187654", 2, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("sajiuaghoghh", "57125875178", 2, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("asiuiuaiutiu", "5284768276", 6, Customer.Type.VIP));
        
        call_manager.displayTable();
    }
}
