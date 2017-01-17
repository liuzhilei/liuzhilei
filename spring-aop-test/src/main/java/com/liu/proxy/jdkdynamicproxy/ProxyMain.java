package com.liu.proxy.jdkdynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by liuzhilei on 2017/1/11.
 * 动态代理特性：
 * 1.继承了proxy类，因为java是单继承，所以动态代理支持接口
 * 2.提供了一个InvocationHandler作为参数的构造方法
 * 3.jdk动态代理的类是必须“实现接口的类”，因为产生的proxy代理类也是实现了这个接口，如果目标类自己新建了方法，proxy类是不能代理这个方法的
 */
public class ProxyMain {
    public static void main(String[] args) {

        MyInvocationHandler handler = new MyInvocationHandler(new HelloWorldImpl1());
        /**
         * Jdk通过java.lang.reflect.Proxy 来实现动态代理，调用proxy的newProxyInstance来获得代理实现类。
         * 对于代理接口的实际处理，是一个java.lang.reflect.InvocationHandler，他提供一个invoke方法实现具体的代理业务逻辑代码。
         */
        HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), new Class[]{HelloWorld.class}, handler);

        helloWorld.sayHello("刘志磊");
    }
}
