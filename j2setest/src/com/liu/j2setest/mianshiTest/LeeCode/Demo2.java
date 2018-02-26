package com.liu.j2setest.mianshiTest.LeeCode;

/**
 * Created by liuzhilei on 2018/1/3.
 * 给定两个非空链表，表示两个非负整数。数字以相反的顺序存储，每个节点包含一个数字，添加这两个数字并将其作为链接列表返回。
 * 您可以假定这两个数字不包含任何前导零，除了数字0本身。
 * <p/>
 * 例
 * 输入：（1→2→3）+（6→8→5）
 * 输出： 7→0→9
 * 说明： 321 + 586 = 907。
 * <p/>
 * 解题思路：
 * 1.需要先定义一个用于进位的变量markIndex
 * 2.根据node.next，while循环链表，第一次循环为1和6相加，假如大于10，markIndex设置为1，用于下次相加的进位。当用完markindex以后，需要重置为0重新记录。
 */
public class Demo2 {

    //链表类
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        ListNode listNode = demo2.new ListNode(1);
        listNode.next = demo2.new ListNode(2);
        listNode.next.next = demo2.new ListNode(3);

        ListNode listNode1 = demo2.new ListNode(6);
        listNode1.next = demo2.new ListNode(8);
        listNode1.next.next = demo2.new ListNode(5);

        demo2.method1(listNode, listNode1);

    }

    public void method1(ListNode node1, ListNode node2) {
        //用于标记进位
        int markIndex = 0;

        while (node1 != null || node2 != null) {
            //临时记录两个node的val
            int num1 = 0;
            int num2 = 0;

            if (node1 != null) {
                num1 = node1.val;
                node1 = node1.next;
            }
            if (node2 != null) {
                num2 = node2.val;
                node2 = node2.next;
            }

            //进位的值加上当前num1和num2的和
            int tempSum = markIndex + num1 + num2;

            //进位重置为0，用于下次记录
            markIndex = 0;

            //相加大于10，记录进位
            if (tempSum >= 10) {
                tempSum = tempSum % 10;
                markIndex++;
            }
            System.out.print(tempSum);
        }

    }


}
