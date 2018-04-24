package com.liu.j2setest.mianshiTest;

/**
 * Created by liuzhilei on 2018/4/23.
 * 单链表反转
 */
public class Test4 {

    class Node {
        Node next;
        Integer value;
    }

    public static void main(String[] args) {
        Node node = new Test4().new Node();
        node.value = 1;

        node.next = new Test4().new Node();
        node.next.value = 2;

        node.next.next = new Test4().new Node();
        node.next.next.value = 3;

        System.out.println(node.value);
        System.out.println(node.next.value);
        System.out.println(node.next.next.value);

        Node node1 = reverseNode(node);
        System.out.println(node1.value);
        System.out.println(node1.next.value);
        System.out.println(node1.next.next.value);

    }

    public static Node reverseNode(Node node) {
        Node cur = node;
        Node b = null;
        while (cur != null) {
            Node a = cur.next; //记录当前节点的下一节点
            cur.next = b;//将b赋给当前节点的next
            b = cur;//当前节点赋给b，用于下次循环将比指向操作节点的next,b循环到最后，就是反转节点的头节点
            cur = a;//记录的a赋给cur，用于下次循环
        }

        return b;
    }
}
