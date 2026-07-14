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
    private class Entry {
        Entry(T value, int order) {
            this.value = value;
            this.order = order;
        }

        private final T value;
        private final int order;
    }

    public HeapTree(Comparator<? super T> comparator) {
        this.elements = new ArrayList<>();
        this.comparator = (entry_a, entry_b) -> {
            int compare_result = comparator.compare(entry_a.value, entry_b.value);
            if (compare_result == 0) {
                return entry_b.order - entry_a.order;
            } else {
                return compare_result;
            }
        };
        this.current_order = 0;
    }

    public void addAll(List<T> list) {
        List<Entry> entry_list = new ArrayList<>();
        for (T object : list) {
            entry_list.add(new Entry(object, this.current_order++));
        }
        this.elements.addAll(entry_list);
        // Fix heap tree to push element with greatest value on top
        this.build();
    }

    public void push(T element) {
        this.elements.add(new Entry(element, this.current_order++));
        // Fix heap tree to push element with greatest value on top
        this.siftUp(elements.size() - 1);
    }

    public void remove(int i) {
        if (i >= this.elements.size()) {
            return;
        }
        // Remove element by index
        this.swap(i, this.elements.size() - 1);
        this.elements.remove(this.elements.size() - 1);
        if (this.elements.size() == 0) {
            this.current_order = 0;
        }
        // If the removed element is the last element then we don't need to fix the tree
        if (i < this.elements.size()) {
            this.siftUp(i);
            this.siftDown(i);
        }
    }

    public T get(int i) {
        return this.elements.get(i).value;
    }

    public int getSize() {
        return this.elements.size();
    }

    public int indexOf(T object) {
        for (int i = 0; i < this.elements.size(); ++i) {
            if (object == this.elements.get(i).value) {
                return i;
            }
        }
        return -1;
    }

    public T pop() {
        if (this.elements.size() == 0) {
            this.current_order = 0;
            return null;
        }
        // We first save the popped element into a variable
        T result = this.elements.get(0).value;
        // Then move the very last element at the bottom of the tree to the root
        this.elements.set(0, this.elements.get(this.elements.size() - 1));
        this.elements.remove(this.elements.size() - 1);
        // Fix heap tree to push element with greatest value on top
        if (!this.elements.isEmpty()) {
            this.siftDown(0);
        }

        return result;
    }

    void swap(int a, int b) {
        Entry temp = this.elements.get(a);
        this.elements.set(a, this.elements.get(b));
        this.elements.set(b, temp);
    }

    int getGreatest(int i) {
        // Self explanatory
        // This function return the greatest value between parent, left child and right
        // child
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
                this.swap(parent, i);

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
            this.swap(greatest, i);
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

    List<Entry> elements;
    Comparator<? super Entry> comparator;
    int current_order;
}
