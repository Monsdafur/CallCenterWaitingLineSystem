/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.call_center_waiting_line_system;
import data_structs.Queue;

/**
 *
 * @author dqhoa
 */
public class Call_Center_Waiting_Line_System {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>(true);
        queue.Enqueue(0);
        queue.Enqueue(3);
        queue.Enqueue(1);
        queue.Enqueue(5);
        queue.Enqueue(2);
        queue.Enqueue(7);
        Integer e;
        do {
            e = queue.Dequeue();
        } while (e != null);
    }
}
