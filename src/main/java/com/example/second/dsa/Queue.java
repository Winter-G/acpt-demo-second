package com.example.second.dsa;

public class Queue {

    Node top;

    public void push(int data){

        Node node = new Node(data);

        // If top is null
        if(top == null){
            top = node;  // Set the new node as the top
        }else{
            Node temp = top; // Create a temporary reference to the top node

            // While temp.next is not null
            while(temp.next != null){
                temp = temp.next;   // Move to the next node in the queue
            }

            // Once reach the last node, set its next pointer to the new node
            temp.next = node;
        }
    }



    public void printQueue(){
        Node temp = top;

        while(temp != null){
            System.out.println(temp.data);
            temp = temp.next;

        }
    }
}
