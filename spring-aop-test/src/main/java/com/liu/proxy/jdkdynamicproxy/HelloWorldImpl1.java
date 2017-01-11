package com.liu.proxy.jdkdynamicproxy;

/**
 * Created by liuzhilei on 2017/1/11.
 */
public class HelloWorldImpl1 implements HelloWorld {
    @Override
    public void sayHello(String name) {
        System.out.println("name" + name);
    }
}
