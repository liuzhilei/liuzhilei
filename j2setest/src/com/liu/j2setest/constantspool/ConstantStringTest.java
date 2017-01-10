package com.liu.j2setest.constantspool;

/**
 * Created by liuzhilei on 2017/1/5.
 */
public class ConstantStringTest {
    String s1 = "Hello";
    String s2 = "Hello";
    String s3 = "Hel" + "lo";
    String s4 = "Hel" + new String("lo");
    String s5 = new String("Hello");
    String s6 = s5.intern();
    String s7 = "H";
    String s8 = "ello";
    String s9 = s7 + s8;

    {
        System.out.println("true: " + s1 == s2);
        System.out.println("true: " + s1 == s3);
        System.out.println("false: " + s1 == s4);
        System.out.println("true: " + s1 == s9);
        System.out.println("false: " + s4 == s5);
        System.out.println("true: " + s1 == s6);
    }

/*    public static void main(String[] args) {




    }*/

}
