package com.liu.j2setest.装箱拆箱;

/**
 * Created by liuzhilei on 2017/7/2.
 * 包装类的==如果不遇到算数运算，不会自动拆箱
 * equals不处理类型转换
 */
public class Test {
    public static void main(String[] args) {
        Integer a = 130;
        Integer b = 130;
        System.out.println(a == b);//false

        Integer c = 130;
        Integer d = 129;
        Integer e = 1;
        System.out.println(c == (d + e));//true 涉及到运算，所以自动拆箱

        Integer f = 130;
        Long g = 130l;
        System.out.println(f.equals(g));//false equals不处理类型转换

        Integer h = 130;
        Long i = 129l;
        Long j = 1l;
        System.out.println(130 == (i + j));//true 涉及到运算，所以自动拆箱

    }
}
