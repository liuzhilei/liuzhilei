package com.liu.j2setest.javaVM.classLoading.被动引用;

/**
 * Created by liuzhilei on 2017/3/7.
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("子类初始化");
    }

    public SubClass(){
        System.out.println("调用子类构造方法");
    }

}
