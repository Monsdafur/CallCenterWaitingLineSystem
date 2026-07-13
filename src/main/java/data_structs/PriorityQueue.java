/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_structs;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 *
 * @author dqhoa
 */
public class PriorityQueue<T> {
    public PriorityQueue(Comparator<? super T> comparator) {
        this.elements = new ArrayList<>();
        this.comparator = comparator;
    }
    
    public void push(T element) {
        // Add new element into the array
        elements.add(element);
        // Fix heap tree to push element with greatest value on top
        build();
    }
    
    public void remove(int i) {
        // Remove element by index
        elements.remove(i);
        // Fix heap tree to push element with greatest value on top
        build();
    }
    
    public T get(int i) {
        return this.elements.get(i);
    }
    
    public int getSize() {
        return this.elements.size();
    }
    
    public T pop() {
        // We first save the popped element into a variable
        T result = this.elements.get(0);
        // Then move the very last element at the bottom of the tree to the root
        this.elements.set(0, this.elements.get(this.elements.size() - 1));
        this.elements.remove(this.elements.size() - 1);
        // Fix heap tree to push element with greatest value on top
        this.build();
        
        return result;
    }
    
    int getLeft(int i) {
        // Formula to get the left child of any tree node
        // index = i * 2
        return (i * 2) >= this.elements.size() ? -1 : (i * 2);
    }
    
    int getRight(int i) {
        // Formula to get the right child of any tree node
        // index = i * 2 + 1
        return (i * 2 + 1) >= this.elements.size() ? -1 : (i * 2 + 1);
    }
    
    int getGreatest(int i) {
        // Self explanatory
        // This function return the greatest value between parent, left child and right child
        // using a custom comparator function
        int left = this.getLeft(i);
        int right = this.getRight(i);
        
        int greatest = i;
        if (left != -1) {
            if (comparator.compare(this.elements.get(left), this.elements.get(i)) > 0) {
                greatest = left;
            }
        }
        if (right != -1) {
            if (comparator.compare(this.elements.get(right), this.elements.get(i)) > 0) {
                greatest = right;
            }
        }
        
        return greatest;
    }
    
    void heapify(int i) {
        // Fetch greatest value in the current node
        int greatest = this.getGreatest(i);
        // If the greatest value between parent left child and right child
        // is not the parent we swap those 2 values
        if (i != greatest) {
            T temp = this.elements.get(i);
            this.elements.set(i, this.elements.get(greatest));
            this.elements.set(greatest, temp);
            // Continue traversing the entire tree
            this.heapify(greatest);
        }
    }
    
    void build() {
        // We begin heapifying at the left most last node that is not a leaf
        // and work our way back to the root
        for (int i = elements.size() / 2 - 1; i >= 0; --i) {
            this.heapify(i);
        }
    }
    
    List<T> elements;
    Comparator<? super T> comparator;
}
