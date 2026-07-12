/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

/**
 *
 * @author dqhoa
 */
public class Customer {
    public enum Type {
        NORMAL,
        VIP
    };
    
    public Customer(String name, String contact, int call_times, Type type) {
        this.name = name;
        this.contact = contact;
        this.call_times = call_times;
        this.type = type;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    public int getCallTimes() {
        return this.call_times;
    }
    
    public Type getType() {
        return type;
    }
    
    public boolean isVip() {
        return this.type == Type.VIP;
    }
    
    public String[] asStringArray() {
        return new String[] {
            name, contact, Integer.toString(call_times)
        };
    }
    
    private String name;
    private String contact;
    private int call_times;
    private Type type;
}
