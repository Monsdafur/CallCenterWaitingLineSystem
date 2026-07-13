/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import data_structs.*;
import java.util.Comparator;
import utilities.*;

/**
 *
 * @author dqhoa
 */
public class CallsManager {
    public CallsManager() {
        Comparator<Customer> comparator = Comparator.comparingInt(Customer::getCallTimes);
        this.priority = new PriorityQueue<>(comparator);
        this.regular = new PriorityQueue<>(comparator);
    }
    
    public void addCustomer(Customer customer) {
        if (customer.isVip()) {
            this.priority.push(customer);
        } else {
            this.regular.push(customer);
        }
    }
    
    public void processCall() {
        List<String[]> customer = new ArrayList<>();
        if (this.priority.getSize() > 0) { // If the priority queue is not empty process it and ignore regular
            customer.add(this.priority.pop().asStringArray());
        } else if (this.regular.getSize() > 0) { // Process regular only if priority queue is empty
            customer.add(this.regular.pop().asStringArray());
        } else {
            System.out.println("No customers to process!");
        }
        
        TableOutput.printTable(LABELS, customer);
    }
    
    public void displayTable() {
        List<String[]> string_list = new ArrayList<>();
        
        for (int i = 0; i < this.priority.getSize(); ++i) {
            string_list.add(this.priority.get(i).asStringArray());
        }
        
        for (int i = 0; i < this.regular.getSize(); ++i) {
            string_list.add(this.regular.get(i).asStringArray());
        }
        
        System.out.print("Customer list (unsorted):");
        TableOutput.printTable(LABELS, string_list);
    }
    
    // Priority queue is preserved for VIP customers
    private PriorityQueue<Customer> priority;
    // Regular queue is preserved for regular customers
    private PriorityQueue<Customer> regular;
    // Headers labels for outputing to table
    private static final String[] LABELS = {"Name", "Contact", "Call times", "Customer type"};
}
