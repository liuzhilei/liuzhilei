package com.liu.j2setest.javaVM.语法糖;

/**
 * Created by liuzhilei on 2017/4/9.
 * 泛型擦除
 * java编译期会将泛型擦除编程原生类型，所以泛型只存在于源码中，是语法糖
 */
public class fanxingcachu {

    /*
    下面两个方法虽然参数不一样，但是仍然不能重载，因为在编译以后，泛型已经擦除，所以两个是一模一样的方法，编译不会通过
    public static void method1(List<Integer> list){
        System.out.println("invoke method1 List<Integer> list");
    }

    public static void method1(List<String> list){
        System.out.println("invoke method1 List<String> list");
    }*/

    /*
    下面的方法 ，jvm书上说可以重载成功，因为对于class文件来说，只要两个方法的描述符不完全一直，就可以共存
    但是我自己写着是不能共存的，做个记录
    public static int method2(List<Integer> list) {
        System.out.println("invoke method2 List<Integer> list");
        return 0;
    }

    public static String method2(List<String> list) {
        System.out.println("invoke method1 List<String> list");
        return "123";
    }*/

    public static void main(String[] args) {
    }
}
