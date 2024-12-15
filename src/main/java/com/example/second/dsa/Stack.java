package com.example.second.dsa;

public class Stack {

    Node top; //a reference variable - default value null

    public void push(int data) {

        // Create a new node with the given data
        Node node = new Node(data);

        // Link the new node to the current top of the stack
        node.next = top;

        // Update the top of the stack as the new node
        top = node;

    }

    public void printStack() {

        // Start from the top of the stack
        Node temp = top;

        // While temp is not null
        while (temp != null) {
            // Print the data of the current node (topmost node initially)
            System.out.println(temp.data);

            // Move to the next node in the stack (linking to the next node)
            temp = temp.next;
        }
    }

    public int pop() {

        // Move the top pointer to the next node in the stack
        top = top.next;

        return top.data;
    }
}
