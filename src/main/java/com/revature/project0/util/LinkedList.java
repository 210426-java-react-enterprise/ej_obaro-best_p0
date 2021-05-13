package com.revature.project0.util;


public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void add(T data) {
        if(data == null){
            throw new IllegalArgumentException("Does not accept null value");
        }
        //declare a newNode to be created
        Node<T> newNode = new Node<T>(data);
        //if empty, head and tail points to newNode
        if (head == null){
            tail = head = newNode;
            head.prevNode = null;
            tail.nextNode = null;
        }else{
            //add newNode to end of list
            tail.nextNode = newNode;
            newNode.prevNode = tail;
            tail = newNode;
            tail.nextNode = null;
        }
        size++;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The provided index would be out of bounds.");
        }

        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.data;
            }
            runner = runner.nextNode;
        }

        return null;
    }
    public T pop(){
        if (head == null){
            return null;
        }
        T soughtData = head.data;
        head = head.nextNode;

        if (head != null){
            head.prevNode = null;
        }else{
            tail = null;
        }
        size--;

        return  soughtData;
    }

    @Override
    public boolean contains(T data) {
        Node<T> runner = head;
        for(int i = 0; i < size; i++){
            if(runner.data == data){
                return true;
            }
            runner = runner.nextNode;
        }
        return false;
    }


    @Override
    public int size() {

        return size;
    }

    @Override
    //prints all the nodes in our linked list
    public void printLinkedList() {
        Node current = head;
        if (head == null) {
            System.out.println("Doubly linked list is empty");
            return;
        }
        while(current != null){
            System.out.println(current.data+" ");
            current = current.nextNode;
        }
    }



    private static class Node<T>{
        T data;
        Node<T> nextNode;
        Node<T> prevNode;
        public Node(T data){
            this.data = data;
        }
        public Node(T data, Node<T> nextNode, Node<T> prevNode){
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }

    }
    public T poll(){
        if (head == null){
            return null;
        }
        T soughtData = head.data;
        head = head.nextNode;
        if (head != null){
            head.prevNode = null;
        }else{
            tail = null;
        }
        size--;

        return soughtData;
    }
    public T peek(){
        return head.data;
    }
    public boolean remove(T data){
        Node<T> runner = head;
        for (int i = 0; i < size; i++){
            if (runner.data == data){
                runner.prevNode = runner.nextNode;
                runner.nextNode.prevNode = runner.prevNode;
                return true;
            }
            runner = runner.nextNode;
        }
        return false;
    }
    public boolean isEmpty(){
        return head == null && tail == null;
    }
}
