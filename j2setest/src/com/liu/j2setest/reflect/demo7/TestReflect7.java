package com.liu.j2setest.reflect.demo7;

import java.lang.reflect.Method;

/**
 * Created by liuzhilei on 2017/7/7.
 * 通过反射机制调用某各类的方法
 */
public class TestReflect7 {

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.liu.j2setest.reflect.demo7.TestReflect7");

        //通过反射调用reflect1方法
        Method method1 = aClass.getMethod("reflect1");
        method1.invoke(aClass.newInstance());

        //通过反射调用reflect2方法
        Method method2 = aClass.getMethod("reflect2", int.class, String.class);
        method2.invoke(aClass.newInstance(), 20, "小明");

        //通过反射调用TestReflect6中的test方法
        Class<?> aClass1 = Class.forName("com.liu.j2setest.reflect.demo6.TestReflect6");
        Method method = aClass1.getMethod("test");
        method.invoke(aClass1.newInstance());
    }

    public void reflect1() {
        System.out.println("Java 反射机制 - 调用某个类的方法1.");
    }

    public void reflect2(int age, String name) {
        System.out.println("Java 反射机制 - 调用某个类的方法2.");
        System.out.println("age -> " + age + ". name -> " + name);
    }
}
