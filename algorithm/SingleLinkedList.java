package com.fujfu.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SingleLinkedList {

    private Node head;
    private int size;


    public SingleLinkedList() {
        this.head = new Node(null);
        this.size = 0;
    }

    /**
     * 链表反转
     *
     */
    private void reverse() {
        Node prev = null;
        Node current = head.next;

        while (current != null) {
            Node next = current.next;
            if (next == null) {
                head.next = current;
            }

            current.next = prev;
            prev = current;
            current = next;
        }
    }

    private Node reverse(Node head) {
        if(head == null || head.next == null){
            return head;
        }

        Node node = reverse(head.next);

        head.next.next = head;
        head.next = null;

        return node;
    }

    public void append(Object data) {
        Node newNode = new Node(data);

        Node temp = head;
        while (temp.next !=null) {
            temp = temp.next;
        }

        temp.next = newNode;
        this.size++;
    }

    private void traverse() {
        log.info("traverse...");
        Node node = head.next; // 有头结点

        while (node != null) {
            System.out.println(node.data.toString());
            node = node.next;
        }
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            append(i);
        }
    }


    @Data
    private static class Node {
        Object data;
        Node next;

        Node (Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node (Object data) {
            this.data = data;
        }
    }
}
