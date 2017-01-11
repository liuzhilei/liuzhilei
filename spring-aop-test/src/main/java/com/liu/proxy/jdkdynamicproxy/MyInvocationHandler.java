package com.liu.proxy.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liuzhilei on 2017/1/11.
 * <p/>
 * 对于代理接口的实际处理，是一个java.lang.reflect.InvocationHandler，invoke方法供实现者提供
 * 相应的逻辑代码，可以对实际的实现进行一些特殊处理，像spring aop的各种advice。
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理之前");
        Object invoke = method.invoke(target, args);
        System.out.println("调用代理之后");
        return invoke;
    }
}
