package com.liu.proxy.jdkdynamicproxy;

/**
 * Created by liuzhilei on 2017/1/11.
 */
public class HelloWorldImpl1 implements HelloWorld {
    @Override
    public void sayHello(String name) {
        System.out.println("jdk动态代理，运行期织入，必须实现接口，因为代理类和目标类实现同一个接口，能代理的只能是接口里面的方法，自定义的不能代理， ，name" + name);
    }
}
