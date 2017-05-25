package com.liu.j2setest.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by liuzhilei on 2017/5/25.
 */
public class GenericSuperclassDemo {

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.getClass());

        //获取父类
        System.out.println(student.getClass().getSuperclass());

        //getGenericSuperclass() 获取带有泛型的父类
        //type是java中所有类型的公共接口
        Type type = student.getClass().getGenericSuperclass();
        System.out.println(type);

        //ParameterizedType 参数化类型，即泛型
        ParameterizedType p = (ParameterizedType) type;

        //p.getActualTypeArguments() 获取的就是泛型类型，因为泛型可能有多个，所以是数组格式
        Class aClass = (Class) p.getActualTypeArguments()[0];
        System.out.println(aClass);

    }
}
