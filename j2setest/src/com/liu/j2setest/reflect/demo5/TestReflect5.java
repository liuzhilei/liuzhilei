package com.liu.j2setest.reflect.demo5;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by liuzhilei on 2017/7/7.
 */
public class TestReflect5 extends Reflect5Ex implements Reflect5Interface {
    private String test1;
    private int test2;
    public Integer test3;

    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.liu.j2setest.reflect.demo5.TestReflect5");

        System.out.println("==========本类属性==========");
        //获取本来的全部属性
        Field[] fields = aClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //获取权限修饰符
            int mod = fields[i].getModifiers();
            String string = Modifier.toString(mod);
            //获取参数类型
            Class<?> type = fields[i].getType();
            System.out.println(string + " " + type.getName() + " " + fields[i].getName());
        }

        System.out.println("==========实现的接口或者父类的属性以及本类的公有属性==========");
        //获取接口，父类，本类的所有公有属性
        Field[] fields1 = aClass.getFields();
        //Field[] fields1 = aClass.getSuperclass().getFields();
        for (int i = 0; i < fields1.length; i++) {
            //获取权限修饰符
            int mod = fields1[i].getModifiers();
            String string = Modifier.toString(mod);
            //获取参数类型
            Class<?> type = fields1[i].getType();
            System.out.println(string + " " + type.getName() + " " + fields1[i].getName());
        }

    }
}
