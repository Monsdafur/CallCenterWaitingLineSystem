/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author dqhoa
 */
public class Customer {
    public enum Type {
        NORMAL,
        VIP
    };
    
    @JsonCreator
    public Customer(
        @JsonProperty("name") String name,
        @JsonProperty("contact") String contact,
        @JsonProperty("call_times") int call_times,
        @JsonProperty("type") Type type
    ) {
        this.name = name;
        this.contact = contact;
        this.call_times = call_times;
        this.type = type;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public void setCallTimes(int call_times) {
        this.call_times = call_times;
    }
    
    public void setType(Type type) {
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
    
    @Override
    public String toString() {
        return String.format("Name: %s; Contact: %s; Call times: %d", this.name, this.contact, this.call_times);
    }
    
    public String[] asStringArray() {
        return new String[] {
            name, contact, Integer.toString(call_times), this.isVip() ? "VIP" : "Regular"
        };
    }
    
    private String name;
    private String contact;
    private int call_times;
    private Type type;
}
