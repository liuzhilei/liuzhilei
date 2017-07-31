package com.liu.j2setest.String;

/**
 * Created by liuzhilei on 2017/7/28.
 */
public class Demo1 {
    public static void main(String[] args) {
        String str = "abc";
        String str1 = "abc";
        String str2 = new String("abc");

        System.out.println(str == str1);//true 指向的都是常量池中的abc
        System.out.println(str == str2);//false str2指向的是堆空间的abc，不是同一个对象
        System.out.println(str == str2.intern());//str2.intern()将引用指向常量池
    }
}
