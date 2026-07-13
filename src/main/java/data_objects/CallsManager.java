/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import data_structs.PriorityQueue;
import utilities.TableOutput;

/**
 *
 * @author dqhoa
 */
public class CallsManager {
    public CallsManager() {
        Comparator<Customer> comparator = Comparator.comparingInt(Customer::getCallTimes);
        this.priority = new PriorityQueue<>(comparator);
        this.regular = new PriorityQueue<>(comparator);
        this.processed_calls = new ArrayList<>();
    }
    
    public void addCustomer(Customer customer) {
        if (customer.isVip()) {
            this.priority.push(customer);
        } else {
            this.regular.push(customer);
        }
    }
    
    public void removeCustomer(int i, Customer.Type type) {
        switch (type) {
            case VIP:
                this.priority.remove(i);
            case NORMAL:
                this.regular.remove(i);
        }
    }
    
    public boolean isEmpty() {
        return this.priority.getSize() == 0 && this.regular.getSize() == 0;
    }
    
    public void processCall() {
        if (this.priority.getSize() > 0) { // If the priority queue is not empty process it and ignore regular
            this.processed_calls.add(this.priority.pop());
        } else if (this.regular.getSize() > 0) { // Process regular only if priority queue is empty
            this.processed_calls.add(this.regular.pop());
        } else {
            System.out.println("No customers to process!");
        }
    }
    
    public void displayHistory() {
        List<String[]> string_list = new ArrayList<>();
        for (int i = 0; i < this.processed_calls.size(); ++i) {
            string_list.add(this.processed_calls.get(i).asStringArray());
        }
        System.out.print("History:");
        TableOutput.printTable(LABELS, string_list);
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
    private final PriorityQueue<Customer> priority;
    // Regular queue is preserved for regular customers
    private final PriorityQueue<Customer> regular;
    // A list to record all processed calls
    private final List<Customer> processed_calls;
    // Headers labels for outputing to table
    private static final String[] LABELS = {"Name", "Contact", "Call times", "Customer type"};
}
