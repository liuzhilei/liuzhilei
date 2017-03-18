package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.test;

/**
 * Created by liuzhilei on 2017/3/17.
 */
public class Test {
    static final int iii = 1;
    static int jjj = 2;
    final int fff = 3;

    public static void main(String[] args) {
        String str = "A";
        String str1 = "B";
        System.out.println(str == str1);

        final int integer = 4;
        final int integer1 = 5;
        System.out.println(integer == integer1);

        System.out.println(iii == jjj);
    }
}
