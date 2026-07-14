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
public class HeapTree<T> {
    public HeapTree(Comparator<? super T> comparator) {
        this.elements = new ArrayList<>();
        this.comparator = comparator;
    }
    
    public void addAll(List<T> list) {
        this.elements.addAll(list);
        // Fix heap tree to push element with greatest value on top
        this.build();
    }
    
    public void push(T element) {
        this.elements.add(element);
        // Fix heap tree to push element with greatest value on top
        this.siftUp(elements.size() - 1);
    }
    
    public void remove(int i) {
        if (i > this.elements.size()) {
            return;
        }
        // Remove element by index
        this.elements.remove(i);
        // Fix heap tree to push element with greatest value on top
        if (i < this.elements.size()) {
            this.siftUp(i);
            this.siftDown(i);
        }
    }
    
    public T get(int i) {
        return this.elements.get(i);
    }
    
    public int getSize() {
        return this.elements.size();
    }
    
    public int indexOf(T object) {
        return this.elements.indexOf(object);
    }
    
    public T pop() {
        // We first save the popped element into a variable
        T result = this.elements.get(0);
        // Then move the very last element at the bottom of the tree to the root
        this.elements.set(0, this.elements.get(this.elements.size() - 1));
        this.elements.remove(this.elements.size() - 1);
        // Fix heap tree to push element with greatest value on top
        if (!this.elements.isEmpty()) {
            this.siftDown(0);
        }
        
        return result;
    }
    
    int getGreatest(int i) {
        // Self explanatory
        // This function return the greatest value between parent, left child and right child
        // using a custom comparator function
        // Formula to get the left child of any tree node
        // index = i * 2 + 1
        int left = (i * 2) + 1;
        // Formula to get the right child of any tree node
        // index = i * 2 + 2
        int right = (i * 2) + 2;
        
        int greatest = i;
        if (left < this.elements.size()) {
            if (comparator.compare(this.elements.get(left), this.elements.get(i)) > 0) {
                greatest = left;
            }
        }
        if (right < this.elements.size()) {
            if (comparator.compare(this.elements.get(right), this.elements.get(greatest)) > 0) {
                greatest = right;
            }
        }
        
        return greatest;
    }
        
    void siftUp(int i) {
        while (i > 0) {
            // The formula to get parent
            // index = (i - 1) / 2
            int parent = (i - 1) / 2;
            // if the child is greater than the parent we swap
            if (comparator.compare(this.elements.get(i), this.elements.get(parent)) > 0) {
                T temp = this.elements.get(i);
                this.elements.set(i, this.elements.get(parent));
                this.elements.set(parent, temp);
                
                i = parent;
            } else {
                break;
            }
        }
    }
    
    void siftDown(int i) {
        // Fetch greatest value in the current node
        int greatest = this.getGreatest(i);
        // If the greatest value between parent left child and right child
        // is not the parent we swap those 2 values
        if (i != greatest) {
            T temp = this.elements.get(i);
            this.elements.set(i, this.elements.get(greatest));
            this.elements.set(greatest, temp);
            // Continue traversing the entire tree
            this.siftDown(greatest);
        }
    }
    
    void build() {
        // We begin siftDowning at the left most last node that is not a leaf
        // and work our way back to the root
        for (int i = elements.size() / 2 - 1; i >= 0; --i) {
            this.siftDown(i);
        }
    }
    
    List<T> elements;
    Comparator<? super T> comparator;
}
