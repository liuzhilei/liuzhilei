package com.liu.j2setest.reflect.demo8;

import java.lang.reflect.Field;

/**
 * Created by liuzhilei on 2017/7/7.
 * 通过反射操作类的属性
 */
public class TestReflect8 {
    private String string = null;

    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("com.liu.j2setest.reflect.demo8.TestReflect8");
        Object o = aClass.newInstance();
        //getDeclaredField 可以调用private类型属性
        Field field = aClass.getDeclaredField("string");
        field.setAccessible(true);
        field.set(o,"java反射机制");

        System.out.println(field.get(o));

    }
}
