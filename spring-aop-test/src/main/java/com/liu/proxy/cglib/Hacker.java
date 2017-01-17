package com.liu.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by liuzhilei on 2017/1/17.
 * 实现了cglib的方法拦截器
 */
public class Hacker implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib方法拦截器，前置操作");
        //注意：这是调用的是method的invokeSuper，而不是invoke，因为是调用的父类的方法
        methodProxy.invokeSuper(o, objects);
        System.out.println("cglib方法拦截器，后置操作");
        return null;
    }
}
