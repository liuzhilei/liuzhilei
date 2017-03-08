package com.liu.j2setest.javaVM.classLoading;

/**
 * Created by liuzhilei on 2017/3/7.
 * 静态语句块只能访问到定义在语句块之前的变量，定义他后面的可以赋值，但是不能访问。
 */
public class Test {

    static int i = 0;
    static {
        i = 1;
        System.out.println(i);
    }

    //static int i = 0;
}
