package com.liu.j2setest.reflect.demo2;

/**
 * Created by liuzhilei on 2017/7/7.
 * 通过一个对象获取完整的包名和类名
 */
public class TestReflect1 {
    public static void main(String[] args) {
        TestReflect1 testReflect1 = new TestReflect1();
        System.out.println(testReflect1.getClass().getName());
    }
}
