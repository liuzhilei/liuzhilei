package com.liu.springtest.spring循环依赖;

/**
 * Created by liuzhilei on 2017/9/22.
 */
public class A {
    private B b;

    public A() {
    }

    public A(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public void hello() {
        b.doHello();
    }

    public void doHello() {
        System.out.println("i am A");
    }

}
