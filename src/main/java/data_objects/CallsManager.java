/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import data_structs.HeapTree;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
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
        this.scanner = new Scanner(System.in);
    }
    
    public void loadFile(String file_path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Customer> json_data = mapper.readValue(new File("data/customers.json"), new TypeReference<List<Customer>>() {});
            this.customers.addAll(json_data);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = this.scanner.nextLine();
        System.out.print("Enter customer contact: ");
        String contact = this.scanner.nextLine();
        System.out.print("Enter customer call times: ");
        int call_times = Math.max(this.scanner.nextInt(), 0); // If call times < 0 we clamp it back to range [0; inf]
        this.scanner.nextLine();
        System.out.print("Enter customer type (1 for VIP 2 for Regular): ");
        int type = this.scanner.nextInt();
        if (type < 1 || type > 2) {
            System.out.println("ERROR: Invalid customer type!");
            return;
        }
        this.scanner.nextLine();
        
        Customer customer = new Customer(name, contact, call_times, type == 1 ? Customer.Type.VIP : Customer.Type.NORMAL);
        this.customers.push(customer);
        
        // Displaying newly added customer
        int order = this.customers.indexOf(customer);
        List<String> strings = new ArrayList<>();
        strings.add(Integer.toString(order + 1));
        Collections.addAll(strings, this.customers.get(order).asStringArray());
        List<String[]> customer_strings = new ArrayList<>();
        customer_strings.add(strings.toArray(new String[0]));
        
        System.out.print("INFO: Added customer: ");
        TableOutput.printTable(LABELS, customer_strings);
    }
    
    public void removeCustomer() {
        System.out.print("Enter customer order: ");
        int order = this.scanner.nextInt();
        this.scanner.nextLine();
        if (order > this.customers.getSize()) {
            System.out.print("ERROR: Customer order out of bounds: ");
            return;
        }
        List<String> strings = new ArrayList<>();
        strings.add(Integer.toString(order));
        Collections.addAll(strings, this.customers.get(order - 1).asStringArray());
        List<String[]> customer_strings = new ArrayList<>();
        customer_strings.add(strings.toArray(new String[0]));
        
        System.out.print("INFO: Added customer: ");
        TableOutput.printTable(LABELS, customer_strings);
        this.customers.remove(order - 1);
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
            strings.add(Integer.toString(i + 1));
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
            strings.add(Integer.toString(i + 1));
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

    private final Scanner scanner;
}
