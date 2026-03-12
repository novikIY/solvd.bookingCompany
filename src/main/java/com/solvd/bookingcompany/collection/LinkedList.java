package com.solvd.bookingcompany.collection;

public class LinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public void save(T value) {
        Node<T> node = new Node<>(value);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
    }

    public T get(int index) {

        if (index < 0 || index >= size) {
            return null;
        }

        Node<T> node;

        if (index < size / 2) {
            node = head;

            for (int i = 0; i < index; i++) {
                node = node.next;
            }

        } else {
            node = tail;

            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        return node.data;
    }

    public void remove(int index) {

        if (index < 0 || index >= size) {
            return;
        }

        Node<T> node;

        if (index < size / 2) {
            node = head;

            for (int i = 0; i < index; i++) {
                node = node.next;
            }

        } else {
            node = tail;

            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
    }

    public int size() {
        return size;
    }
}
