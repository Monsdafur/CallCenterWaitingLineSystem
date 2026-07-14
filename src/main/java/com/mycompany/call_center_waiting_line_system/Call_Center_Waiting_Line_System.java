/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.call_center_waiting_line_system;
import com.fasterxml.jackson.core.type.TypeReference;
import data_objects.CallsManager;
import data_objects.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author dqhoa
 */
public class Call_Center_Waiting_Line_System {
    public static void main(String[] args) {
        CallsManager call_manager = new CallsManager();
        call_manager.loadFile("data/customers.json");
        
        call_manager.displayTable();
        System.out.print("\n");
        while (!call_manager.isEmpty()) {
            call_manager.processCall();
        }
        call_manager.displayHistory();
    }
}
