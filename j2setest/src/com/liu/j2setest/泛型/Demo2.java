package com.liu.j2setest.泛型;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by liuzhilei on 2017/10/30.
 * 获取运行时的泛型类型信息
 * 只能获取到父类的，不能获取当前类
 */
public class Demo2{

    public static void main(String[] args) {
        Son son = new Son();
        //获取带有泛型的父类
        Type type = son.getClass().getGenericSuperclass();
        System.out.println(type);

        //参数化类型，即获取泛型
        ParameterizedType p = (ParameterizedType) type;
        //获取泛型
        Class aClass = (Class)p.getActualTypeArguments()[0];
        System.out.println("泛型信息为：" + aClass);


    }
}
