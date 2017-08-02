package com.liu.j2setest.reflect;

import java.lang.reflect.Method;

/**
 * Created by liuzhilei on 2017/8/1.
 * -XX:+TraceClassLoading 动态跟踪类的加载
 *
 * 执行该方法，看控制台输出可以发现，调用method.invoke()方法前15次的时候，正常输出；在调用第16次之前，jvm虚拟机进行了
 * 一些优化，打印了{@code [Loaded sun.reflect.GeneratedMethodAccessor1 from __JVM_DefineClass__]}
 * method.invoke()实际上是调用的是MethodAccessor.invoke()方法来实现的，该方法有两个实现，一个是java版本，一个是native版本。
 * java实现的版本在初始化的时候需要较长时间，但是成为热点代码编译成机器码以后运行速度很快；native版本启动非常快，但是运行时间
 * 长了以后性能就不如java版本，源码中有一个计数器，每调用一次就+1，这个阈值是15，所以invoke调用超过15次，就从native版本
 * 切换为java版本，这个切换调用了源码的{@link sun.reflect.MethodAccessorGenerator#generateMethod}方法切换为java版本的。
 */
public class MethodInvoke {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.liu.j2setest.reflect.MethodInvoke");
        Object o = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("test", Integer.class);
        for (int i = 0; i < 16; i++) {
            method.invoke(o, i);
        }

    }

    private void test(Integer i) {
        System.out.println(i);
    }
}
