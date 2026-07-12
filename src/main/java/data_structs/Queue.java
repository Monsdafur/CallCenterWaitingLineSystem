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
public class Queue<T> {
    public Queue(boolean debug) {
        this.debug = debug; // debug option allow printing every enqueued elements
    }

    public Node<T> Enqueue(T element) {
        Node<T> node = new Node<>(element, null, null);
        if(this.size == 0) {
            // if the queue is initially empty then head = tails = <enqueued element>
            this.head = node;
            this.tail = node;
        }
        else {
            // since queue is FIFO the enqueued element must be last element in the queue
            // therefore tail = <enqueued element>
            this.tail.setNext(node);
            this.tail = node;
        }
        
        // only log message when debug option is enabled via construction
        // also a toString() override is a recommened for custom objects
        if (this.debug) {
            System.out.println(String.format("Enqueued element: %s", node.get().toString()));
        }
        this.size++;
        return node;
    }
    
    public T Dequeue() {
        // we will ignore if the queue is empty
        if (this.size == 0) {
            if (this.debug) {
                System.out.println("Queue empty!");
            }
            return null;
        }
        
        // we fetch the data in the head and save it before modifying the queue
        Node<T> node = this.head;
        
        if (this.size == 1) { // if size is 1 then the queue should be empty post dequeue
            this.head = null;
            this.tail = null;
        }
        else { // if the size is greater than 1 then the head should now be it's next element
            this.head = node.getNext();
            this.head.setPrevious(null);
        }
        
        // only log message when debug option is enabled via construction
        // also a toString() override is a recommened for custom objects
        if (this.debug) {
            System.out.println(String.format("Dequeued element: %s", node.get().toString()));
        }
        
        this.size--;
        // we only need the value of the node so we unwrap it
        return node.get();
    }
    
    public int getSize() {
        return this.size;
    }
    
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        
        for (Node<T> element = this.head; element != null; element = element.getNext()) {
            list.add(element.get());
        }
        return list;
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private boolean debug;
}
