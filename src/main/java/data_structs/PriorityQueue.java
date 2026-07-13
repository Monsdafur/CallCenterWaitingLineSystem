/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_structs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dqhoa
 */
public class PriorityQueue {
    public PriorityQueue() {
        elements = new ArrayList<>();
    }
    
    public void push(Integer element) {
        elements.add(element);
        build();
    }
    
    public Integer get(int i) {
        return this.elements.get(i);
    }
    
    public int getSize() {
        return this.elements.size();
    }
    
    public Integer pop() {
        Integer result = this.elements.get(0);
        this.elements.set(0, this.elements.get(this.elements.size() - 1));
        this.elements.remove(this.elements.size() - 1);
        this.build();
        return result;
    }
    
    int getLeft(int i) {
        return (i * 2) >= this.elements.size() ? -1 : (i * 2);
    }
    
    int getRight(int i) {
        return (i * 2 + 1) >= this.elements.size() ? -1 : (i * 2 + 1);
    }
    
    int getGreatest(int i) {
        int left = this.getLeft(i);
        int right = this.getRight(i);
        
        int greatest = i;
        if (left != -1) {
            if (this.elements.get(left) > this.elements.get(i)) {
                greatest = left;
            }
        }
        if (right != -1) {
            if (this.elements.get(right) > this.elements.get(i)) {
                greatest = right;
            }
        }
        
        return greatest;
    }
    
    void heapify(int i) {
        int greatest = this.getGreatest(i);
        if (i != greatest) {
            int temp = this.elements.get(i);
            this.elements.set(i, this.elements.get(greatest));
            this.elements.set(greatest, temp);
            this.heapify(greatest);
        }
    }
    
    void build() {
        for (int i = elements.size() / 2 - 1; i >= 0; --i) {
            this.heapify(i);
        }
    }
    
    List<Integer> elements;
}
