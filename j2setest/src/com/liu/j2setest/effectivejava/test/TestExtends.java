package com.liu.j2setest.effectivejava.test;

/**
 * Created by liuzhilei on 2017/2/10.
 */
public class TestExtends extends Test {

    public TestExtends(int i) {
        //super(i);
    }

    public static void main(String[] args) {
        String str = new String("123");

        TestExtends testExtends = new TestExtends(1);
    }
    /**
     * 继承，子类首先会调用父类的默认构造函数
     */
}
