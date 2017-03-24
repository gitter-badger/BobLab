package com.bobwang.hackerrank;

import java.util.Scanner;

/**
 * Created by cwang on 3/24/17.
 */
public class Day24 {
    public static Node insert(Node head, int data) {
        Node p = new Node(data);
        if (head == null)
            head = p;
        else if (head.next == null)
            head.next = p;
        else {
            Node start = head;
            while (start.next != null)
                start = start.next;
            start.next = p;

        }
        return head;
    }

    public static Node removeDuplicates(Node head) {
        //Write your code here
        Node dummyHead = head;
        Node next = head.next;
        while (next != null) {
            if (next.data == head.data) {
                next = next.next;
                head.next=next;
            } else {
                head = next;
                next = head.next;
            }
        }
        return dummyHead;
    }

    public static void display(Node head) {
        Node start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Node head = null;
        int T = sc.nextInt();
        while (T-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        head = removeDuplicates(head);
        display(head);
    }
}

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}
