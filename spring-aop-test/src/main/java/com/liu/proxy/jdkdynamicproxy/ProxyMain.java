package com.liu.proxy.jdkdynamicproxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by liuzhilei on 2017/1/11.
 * 动态代理特性：
 * 1.继承了proxy类，因为java是单继承，所以动态代理支持接口
 * 2.提供了一个InvocationHandler作为参数的构造方法
 * 3.jdk动态代理的类是必须“实现接口的类”，因为产生的proxy代理类也是实现了这个接口，如果目标类自己新建了方法，proxy类是不能代理这个方法的
 */
public class ProxyMain {
    public static void main(String[] args) throws Exception{
        HelloWorldImpl1 realHelloWorld = new HelloWorldImpl1();
        MyInvocationHandler handler = new MyInvocationHandler(realHelloWorld);
        System.out.println(handler);
        /**
         * Jdk通过java.lang.reflect.Proxy 来实现动态代理，调用proxy的newProxyInstance来获得代理实现类。
         * 对于代理接口的实际处理，是一个java.lang.reflect.InvocationHandler，他提供一个invoke方法实现具体的代理业务逻辑代码。
         */
        HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), new Class[]{HelloWorld.class}, handler);

        System.out.println("生成的helloWorld是否为动态代理类："+Proxy.isProxyClass(helloWorld.getClass()));

        System.out.println(Proxy.getInvocationHandler(helloWorld));

        //ProxyGenerator类用于生成代理类的字节码文件
        byte[] bytes = ProxyGenerator.generateProxyClass("com.sun.proxy.$Proxy0", new Class[]{helloWorld.getClass()});

        //将字节数组写入文件
        File file = new File("/Users/liuzhilei/proxyclass/test.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        helloWorld.sayHello("刘志磊");
    }
}
