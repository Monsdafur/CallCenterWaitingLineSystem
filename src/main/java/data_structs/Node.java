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
    
    public T Get() {
        return this.data;
    }
    
    public Node<T> GetPrevious() {
        return this.previous;
    }
    
    public Node<T> GetNext() {
        return this.next;
    }
    
    public Queue<T> GetOwner() {
        return this.list_owner;
    }
    
    public void SetPrevious(Node<T> previous) {
        this.previous = previous;
    }
    
    public void SetNext(Node<T> next) {
        this.next = next;
    }
    
    public void SetOwner(Queue<T> owner) {
        this.list_owner = owner;
    }
    
    private T data;
    private Node<T> next;
    private Node<T> previous;
    private Queue<T> list_owner;
}
