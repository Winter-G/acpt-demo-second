package com.example.second.dsa;

public class Test {

    public static void main(String[] args) {

        // Create a new Stack instance
        Stack stack = new Stack();


        // Push 10 onto the stack, making it the first (top) element
        stack.push(10);

        // Push 20 onto the stack, making it the new top and linking it to the previous top (10)
        stack.push(20);

        // Push 30 onto the stack, making it the new top and linking it to the previous top (20)
        stack.push(30);

        stack.push(40);



        // Create a new queue instance
        Queue queue = new Queue();

        // Add elements to the queue
        queue.push(10);  // Push 10 into the queue
        queue.push(20);  // Push 20 into the queue
        queue.push(30);  // Push 30 into the queue
        queue.push(40);  // Push 40 into the queue


    }
}
