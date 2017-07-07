package com.liu.j2setest.reflect.demo2;

/**
 * Created by liuzhilei on 2017/7/7.
 * 实例化class对象
 */
public class TestReflect2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class class1 = null;
        Class class2 = null;
        Class class3 = null;

        class1 = Class.forName("com.liu.j2setest.reflect.demo2.TestReflect2");
        class2 = new TestReflect2().getClass();
        class3 = TestReflect2.class;

        System.out.println("类名称，class1：" + class1);
        System.out.println("类名称，class2：" + class1);
        System.out.println("类名称，class3：" + class1);

    }
}
