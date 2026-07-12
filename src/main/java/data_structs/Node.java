/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_structs;

/**
 *
 * @author dqhoa
 * @param <T>
 */
public class Node<T> {
    public Node(T data, Node<T> previous, Node<T> next) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
    public T get() {
        return this.data;
    }
    
    public Node<T> getPrevious() {
        return this.previous;
    }
    
    public Node<T> getNext() {
        return this.next;
    }
    
    public Queue<T> getOwner() {
        return this.list_owner;
    }
    
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
    
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    public void setOwner(Queue<T> owner) {
        this.list_owner = owner;
    }
    
    private T data;
    private Node<T> next;
    private Node<T> previous;
    private Queue<T> list_owner;
}
