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
        }
        else { // this part is basically the same but with regular map instead of priority map
            this.regular.push(customer);
        }
    }
    
    public void processCall() {
        List<String[]> customer = new ArrayList<>();
        if (this.priority.getSize() > 0) {
            customer.add(this.priority.pop().asStringArray());
        } else if (this.regular.getSize() > 0) {
            customer.add(this.regular.pop().asStringArray());
        }
        else {
            System.out.println("No customers to process!");
        }
        
        System.out.print("No customers to process!");
        TableOutput.printTable(LABELS, customer);
    }
    
    public void displayTable() {
        List<String[]> string_list = new ArrayList<>();
        
        // displaying priority queue
        for (int i = 0; i < this.priority.getSize(); ++i) {
            string_list.add(this.priority.get(i).asStringArray());
        }
        
        // displaying regular queue
        for (int i = 0; i < this.regular.getSize(); ++i) {
            string_list.add(this.regular.get(i).asStringArray());
        }
        
        System.out.print("Customer queue:");
        TableOutput.printTable(LABELS, string_list);
    }
    
    // the treemap data struct keeps all queue sorted to the correct order base on the call times
    // priority map is preserved for VIP customer queues
    private PriorityQueue<Customer> priority;
    // regular map is preserved for regular customer queues
    private PriorityQueue<Customer> regular;
    // headers labels for outputing to table
    private static final String[] LABELS = {"Name", "Contact", "Call times", "Customer type"};
}
