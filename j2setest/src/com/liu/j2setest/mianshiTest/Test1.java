package com.liu.j2setest.mianshiTest;

/**
 * Created by liuzhilei on 2017/3/21.
 */
public class Test1 {
    static {
        System.out.println(1);
    }

    public Test1() {
        System.out.println(2);
    }
}

class subTest1 extends Test1 {
    static {
        System.out.println("A");
    }

    public subTest1() {
        System.out.println("B");
    }
}

class Test1Main {
    public static void main(String[] args) {
        //Test1 test1 = new subTest1();
        //test1 = new subTest1();
        subTest1 subTest1 = new subTest1();
    }
}
