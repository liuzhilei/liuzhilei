package com.liu.j2setest.static用法;


/**
 * Created by liuzhilei on 2017/6/13.
 * static用法：
 * 1.静态变量
 * 2.静态方法
 * 3.静态代码块
 * 4.静态内部类
 * 5.静态导包，可以导入指定类的静态方法
 */
public class StaticTest {

    private static int staticInt = 0;

    public static void test(){

    }

    static {
        System.out.println(111);
    }

    private static class neiClass{
        //静态初始化，jvm保证线程安全
        private static StaticTest staticTest = new StaticTest();
    }

    public static void main(String[] args) {
        //static 可以静态导包，导入指定类的静态方法
        //mainTest();
    }

}
