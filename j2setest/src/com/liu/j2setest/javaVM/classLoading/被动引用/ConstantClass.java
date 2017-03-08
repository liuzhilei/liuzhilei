package com.liu.j2setest.javaVM.classLoading.被动引用;


/**
 * Created by liuzhilei on 2017/3/7.
 */
public class ConstantClass {
    static {
        System.out.println("ConstantClass 初始化");
    }

    //public static final int ConstName = 456;
    public static final String str = "hello";
}
