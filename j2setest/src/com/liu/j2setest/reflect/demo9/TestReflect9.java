package com.liu.j2setest.reflect.demo9;

import java.lang.reflect.Array;

/**
 * Created by liuzhilei on 2017/7/7.
 * 通过反射取得并且修改数组的信息
 */
public class TestReflect9 {
    public static void main(String[] args) {
        int[] temp = {1, 2, 3, 4, 5};
        Class<?> aClass = temp.getClass().getComponentType();
        System.out.println("数组类型：" + aClass.getName());
        System.out.println("数组长度：" + Array.getLength(temp));
        System.out.println("数组的第一个元素：" + Array.get(temp, 0));
        //修改数组的第一个元素
        Array.set(temp, 0, 100);
        System.out.println("修改后的数组的第一个元素是：" + Array.get(temp, 0));

    }
}
