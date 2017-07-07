package com.liu.j2setest.reflect.demo3;

/**
 * Created by liuzhilei on 2017/7/7.
 * 获取一个对象的父类和实现的接口
 */
public class TestReflect3 implements TestInterface {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.liu.j2setest.reflect.demo3.TestReflect3");

        /**
         * getGenericSuperclass() 获取带有泛型的父类
         * 获取父类
         */
        Class<?> superclass = clazz.getSuperclass();
        System.out.println("父类是：" + superclass.getName());

        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("实现的接口有：");
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i].getName());
        }
    }
}
