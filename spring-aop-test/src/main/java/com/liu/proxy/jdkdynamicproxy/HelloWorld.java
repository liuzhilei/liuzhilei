package com.liu.proxy.jdkdynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by liuzhilei on 2017/1/11.
 * jdkd动态代理
 * 如果这个接口不是public的，因为访问权限问题，生成的代理类会和这个接口在同一个包下
 * {@link Proxy.ProxyClassFactory#apply(java.lang.ClassLoader, java.lang.Class[])}方法有解释
 */
public interface HelloWorld {

    void sayHello(String name);
}
