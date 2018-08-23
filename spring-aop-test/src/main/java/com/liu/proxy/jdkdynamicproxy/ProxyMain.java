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
        MyInvocationHandler handler = new MyInvocationHandler(new HelloWorldImpl1());

        //获得代理实现类
        HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), new Class[]{HelloWorld.class}, handler);
        /**
         * 这两个代理类是同一个代理类，源码里面有解释，生成代理类会放入内存，下次再获取的时候，直接获取。
         * {@link java.lang.reflect.WeakCache#get(java.lang.Object, java.lang.Object)} 该方法有解释
         */
        HelloWorld helloWorld11 = (HelloWorld) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(), new Class[]{HelloWorld.class}, handler);


        helloWorld.sayHello("刘志磊");

        //代理类写入文件
        writeToClass();
    }

    private static void writeToClass() throws Exception{
        //ProxyGenerator类用于生成代理类的字节码文件,动态代理生成字节码文件同样用的该方法
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy", HelloWorldImpl1.class.getInterfaces());

        //将字节数组写入文件
        File file = new File("/Users/liuzhilei/proxyclass/$Proxy.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();

    }
}
