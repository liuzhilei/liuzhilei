package com.liu.j2setest.javaVM.classLoading.被动引用;

/**
 * Created by liuzhilei on 2017/3/7.
 */
public class SuperClass {
    static {
        System.out.println("父类初始化");
    }

    public SuperClass(){
        System.out.println("调用父类构造方法");
    }

    public static int value = 123;
}
