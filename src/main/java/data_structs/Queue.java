/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_structs;

/**
 *
 * @author dqhoa
 */
public class Queue<T> {
    public Queue(boolean debug) {
        this.debug = debug;
    }

    public Node<T> Enqueue(T element) {
        Node<T> node = new Node<>(element, null, null);
        if(this.size == 0) {
            this.head = node;
            this.tail = node;
        }
        else {
            this.tail.SetNext(node);
            this.tail = node;
        }
        
        // only log message when debug option is enabled via construction
        // also a toString() override is a recommened for custom objects
        if (this.debug) {
            System.out.println(String.format("Enqueued element: %s", node.Get().toString()));
        }
        this.size++;
        return node;
    }
    
    public T Dequeue() {
        if (this.size == 0) {
            if (this.debug) {
                System.out.println("Queue empty!");
            }
            return null;
        }
        Node<T> node = this.head;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.head = node.GetNext();
            this.head.SetPrevious(null);
        }
        
        // only log message when debug option is enabled via construction
        // also a toString() override is a recommened for custom objects
        if (this.debug) {
            System.out.println(String.format("Dequeued element: %s", node.Get().toString()));
        }
        
        this.size--;
        return node.Get();
    }
    
    public int GetSize() {
        return this.size;
    }
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private boolean debug;
}
