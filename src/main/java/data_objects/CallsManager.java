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
                int compare_result = ca.getCallTimes() - cb.getCallTimes();
                if (compare_result == 0) {
                    return cb.getOrder() - ca.getOrder();
                } else {
                    return compare_result;
                }
            } else if (ca.isVip() && !cb.isVip()) { // If customer A is VIP but customer B is not we prioritize customer
                                                    // A and vice versa
                return 1;
            } else {
                return -1;
            }
        };
        this.customers = new HeapTree<>(comparator);
        this.processed_calls = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.current_order = 1;
    }

    public void loadFile(String file_path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Customer> json_data = mapper.readValue(new File("data/customers.json"),
                    new TypeReference<List<Customer>>() {
                    });
            for (Customer customer : json_data) {
                customer.setOrder(this.current_order++);
            }
            this.customers.addAll(json_data);
        } catch (IOException e) {
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

        Customer customer = new Customer(name, contact, call_times,
                type == 1 ? Customer.Type.VIP : Customer.Type.NORMAL);
        customer.setOrder(this.current_order++);
        this.customers.push(customer);
        this.displaySingleCustomer(customer);
    }

    public void removeCustomer() {
        System.out.print("Enter customer order: ");
        int order = this.scanner.nextInt();
        this.scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < this.customers.getSize(); ++i) {
            if (this.customers.get(i).getOrder() == order) {
                this.displaySingleCustomer(this.customers.get(i));
                this.customers.remove(i);
                found = true;
                break;
            }
        }
        if (this.customers.getSize() == 0) {
            this.current_order = 1;
        }

        if (!found) {
            System.out.print("ERROR: Customer order out of bounds: ");
        }
    }
    
    public void editCustomer() {
        System.out.print("Enter customer order: ");
        int order = this.scanner.nextInt();
        this.scanner.nextLine();
        boolean found = false;
        int index = 0;
        for (int i = 0; i < this.customers.getSize(); ++i) {
            if (this.customers.get(i).getOrder() == order) {
                this.displaySingleCustomer(this.customers.get(i));
                index = i;
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.print("ERROR: Customer order out of bounds: ");
            return;
        }
        
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

        Customer customer = new Customer(name, contact, call_times,
                type == 1 ? Customer.Type.VIP : Customer.Type.NORMAL);
        customer.setOrder(order);
        this.customers.set(index, customer);
        System.out.print("Customer edited successfully");
        this.displaySingleCustomer(customer);
    }

    public boolean isEmpty() {
        return this.customers.getSize() == 0;
    }

    public void processCall(boolean display_prcoessed_customer) {
        if (this.customers.getSize() > 0) {
            Customer processed_customer = this.customers.pop();
            if (this.customers.getSize() == 0) {
                this.current_order = 1;
            }
            if (display_prcoessed_customer) {
                this.displaySingleCustomer(processed_customer);
            }
            this.processed_calls.add(processed_customer);
        } else {
            System.out.println("No customers to process!");
        }
    }
    
    public void clearHistory() {
        this.processed_calls.clear();
    }

    public void displayHistory() {
        List<String[]> string_list = new ArrayList<>();
        for (Customer customer : this.processed_calls) {
            string_list.add(customer.asStringArray());
        }
        System.out.print("History:");
        TableOutput.printTable(LABELS, string_list);
    }

    public void displayTable() {
        List<String[]> string_list = new ArrayList<>();

        for (int i = 0; i < this.customers.getSize(); ++i) {
            string_list.add(this.customers.get(i).asStringArray());
        }

        System.out.print("Customer list (unsorted):");
        TableOutput.printTable(LABELS, string_list);
    }

    void displaySingleCustomer(Customer customer) {
        List<String[]> string_list = new ArrayList<>();
        string_list.add(customer.asStringArray());
        TableOutput.printTable(LABELS, string_list);
    }

    // Regular queue is preserved for regular customers
    private final HeapTree<Customer> customers;
    // A list to record all processed calls
    private final List<Customer> processed_calls;
    // Headers labels for outputing to table
    private static final String[] LABELS = { "Order", "Name", "Contact", "Call times", "Customer type" };

    private final Scanner scanner;

    private int current_order;
}
