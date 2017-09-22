package com.liu.springtest.spring循环依赖;

/**
 * Created by liuzhilei on 2017/9/22.
 */
public class B {
    private C c;

    public B() {
    }

    public B(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public void hello() {
        c.doHello();
    }

    public void doHello() {
        System.out.println("i am B");
    }

}
