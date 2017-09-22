package com.liu.springtest.spring循环依赖;

/**
 * Created by liuzhilei on 2017/9/22.
 */
public class C {
    private A a;

    public C() {
    }

    public C(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public void hello() {
        a.doHello();
    }

    public void doHello() {
        System.out.println("i am c");
    }

}
