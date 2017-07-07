package com.liu.j2setest.reflect.demo4;

import java.lang.reflect.Constructor;

/**
 * Created by liuzhilei on 2017/7/7.
 * 通过某各类的全部构造函数，利用反射实例化对象
 */
public class TestReflect4 {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("com.liu.j2setest.reflect.demo4.User");

        /**
         * 方法一：实例化默认构造方法，构造对象
         */
        User user = (User) aClass.newInstance();
        user.setName("小明");
        user.setAge(10);
        System.out.println(user);

        /**
         * 方法二：找出类的所有构造函数
         * 注意：getConstructors()获取的是构造器的栈，先入后出，所以无参构造函数在最后
         */
        Constructor<?>[] constructors = aClass.getConstructors();
        //找出每个构造函数的参数类型
        for (int i = 0; i < constructors.length; i++) {
            Class<?>[] types = constructors[i].getParameterTypes();
            System.out.println("第" + i + "个构造函数的参数类型（");
            for (int j = 0; j < types.length; j++) {
                String typesName = types[j].getName();
                System.out.println(typesName);
            }
            System.out.println("）");
        }

        //利用构造函数赋值
        User user1 = (User) constructors[0].newInstance("小兰", 20);
        System.out.println("利用第一个构造函数：" + user1);

        User user2 = (User) constructors[1].newInstance("小红");
        System.out.println("利用第二个构造函数：" + user2);

        User user3 = (User) constructors[2].newInstance();
        System.out.println("利用第三个构造函数：" + user3);


    }
}
