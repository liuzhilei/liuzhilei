package com.liu.j2setest.reflect;

/**
 * @Auther: liuzhilei
 * @Date: 2019/8/15
 * @Description:
 */
public class ReflectDemo {

    public void test(String str){
        System.out.println(str);
    }

    public void test(int i){
        System.out.println(i);
    }

    public void test(int i, long l){
        System.out.println(i);
        System.out.println(l);
    }
}
