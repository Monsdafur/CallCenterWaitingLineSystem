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
import utilities.*;

/**
 *
 * @author dqhoa
 */
public class CallsManager {
    public CallsManager() {
        this.priority = new TreeMap<>();
        this.regular = new TreeMap<>();
    }
    
    public void addCustomer(Customer customer) {
        if (customer.isVip()) {
            if (!priority.containsKey(customer.getCallTimes())) { // if there hasn't any customers with the same call times in the map we add a new one
                Queue<Customer> queue = new Queue<>(false);
                this.priority.put(customer.getCallTimes(), queue);
            }
            else { // otherwise the enqueue the customer into the designated call time score key value in the treemap 
                this.priority.get(customer.getCallTimes()).Enqueue(customer);
            }
        }
        else { // this part is basically the same but with regular map instead of priority map
            if (!regular.containsKey(customer.getCallTimes())) {
                Queue<Customer> queue = new Queue<>(false);
                this.regular.put(customer.getCallTimes(), queue);
            }
            else {
                this.regular.get(customer.getCallTimes()).Enqueue(customer);
            }
        }
    }
    
    public void processCall() {
        if (!priority.isEmpty()) {
            priority.lastEntry().getValue().Dequeue();
        } else if (!regular.isEmpty()) {
            regular.lastEntry().getValue().Dequeue();
        }
    }
    
    public void displayTable() {
        List<String[]> priority_string_list = new ArrayList<>();
        
        for (Map.Entry<Integer, Queue<Customer>> entry : this.priority.entrySet()) {
            List<Customer> customers = entry.getValue().toList();
            for (Customer customer : customers) {
                priority_string_list.add(customer.asStringArray());
            }
        }
        
        TableOutput.printTable(LABELS, priority_string_list);
    }
    
    // the treemap data struct keeps all queue sorted to the correct order base on the call times
    // priority map is preserved for VIP customer queues
    private TreeMap<Integer, Queue<Customer>> priority;
    // regular map is preserved for regular customer queues
    private TreeMap<Integer, Queue<Customer>> regular;
    // headers labels for outputing to table
    private static final String[] LABELS = {"Name", "Contact", "Call times"};
}
