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
        call_manager.addCustomer(new Customer("asuidhasiudgasd", "187341287", 1, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("oihgsoibiasdfif", "98657279634", 6, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("fiuvgiuia", "1374187654", 4, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("sajiuaghoghh", "57125875178", 11, Customer.Type.VIP));
        call_manager.addCustomer(new Customer("asiuiuaiutiu", "5284768276", 2, Customer.Type.VIP));
        
        call_manager.addCustomer(new Customer("sdiausdaisd", "187341287", 9, Customer.Type.NORMAL));
        call_manager.addCustomer(new Customer("hkaihaihg", "98657279634", 7, Customer.Type.NORMAL));
        call_manager.addCustomer(new Customer("ztfasgdgg", "1374187654", 1, Customer.Type.NORMAL));
        call_manager.addCustomer(new Customer("oiaisdhbnh", "57125875178", 3, Customer.Type.NORMAL));
        call_manager.addCustomer(new Customer("iasyduy", "5284768276", 2, Customer.Type.NORMAL));
        
        for (int i = 0; i < 10; ++i) {
            call_manager.processCall();
        }
    }
}
