/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import data_structs.HeapTree;
import java.util.Collections;
import utilities.TableOutput;

/**
 *
 * @author dqhoa
 */
public class CallsManager {
    public CallsManager() {
        Comparator<Customer> comparator = (ca, cb) -> {
            if (ca.isVip() == cb.isVip()) { // If 2 customers are of the same type we do manual compare
                return ca.getCallTimes() - cb.getCallTimes();
            } else if (ca.isVip() && !cb.isVip()) { // If customer A is VIP but customer B is not we prioritize customer A and vice versa
                return 1;
            }
            else {
                return -1;
            }
        };
        this.customers = new HeapTree<>(comparator);
        this.processed_calls = new ArrayList<>();
    }
    
    public void addCustomer(Customer customer) {
        this.customers.push(customer);
    }
    
    public void removeCustomer(int i) {
        this.customers.remove(i);
    }
    
    public boolean isEmpty() {
        return this.customers.getSize() == 0;
    }
    
    public void processCall() {
        if (this.customers.getSize() > 0) { // If the priority queue is not empty process it and ignore regular
            this.processed_calls.add(this.customers.pop());
        } else {
            System.out.println("No customers to process!");
        }
    }
    
    public void displayHistory() {
        List<String[]> string_list = new ArrayList<>();
        for (int i = 0; i < this.processed_calls.size(); ++i) {
            List<String> strings = new ArrayList<>();
            strings.add(Integer.toString(i));
            Collections.addAll(strings, this.processed_calls.get(i).asStringArray());
            string_list.add(strings.toArray(new String[0]));
        }
        System.out.print("History:");
        TableOutput.printTable(LABELS, string_list);
    }
    
    public void displayTable() {
        List<String[]> string_list = new ArrayList<>();
        
        for (int i = 0; i < this.customers.getSize(); ++i) {
            List<String> strings = new ArrayList<>();
            strings.add(Integer.toString(i));
            Collections.addAll(strings, this.customers.get(i).asStringArray());
            string_list.add(strings.toArray(new String[0]));
        }
        
        System.out.print("Customer list (unsorted):");
        TableOutput.printTable(LABELS, string_list);
    }
    
    // Regular queue is preserved for regular customers
    private final HeapTree<Customer> customers;
    // A list to record all processed calls
    private final List<Customer> processed_calls;
    // Headers labels for outputing to table
    private static final String[] LABELS = {"Order", "Name", "Contact", "Call times", "Customer type"};
}
