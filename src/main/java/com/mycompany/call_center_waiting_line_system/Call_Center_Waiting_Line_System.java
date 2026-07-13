/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.call_center_waiting_line_system;
import data_objects.*;
import data_structs.PriorityQueue;

/**
 *
 * @author dqhoa
 */
public class Call_Center_Waiting_Line_System {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.push(8);
        queue.push(3);
        queue.push(11);
        queue.push(1);
        queue.push(6);
        queue.push(12);
        queue.push(4);
        queue.push(9);
        queue.push(2);
        queue.push(10);
        queue.push(7);
        queue.push(5);
        
        while(queue.getSize() > 0) {
            System.out.println(queue.pop());
        }
    }
}
