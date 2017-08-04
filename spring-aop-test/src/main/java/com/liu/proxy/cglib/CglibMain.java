package com.liu.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;

/**
 * Created by liuzhilei on 2017/1/17.
 * cglib代理，代理类就是一个目标类的子类。即运行期织入，重新组装成一个子类。
 * 总结：
 * cglib 创建某个类A的动态代理类的模式是：
 1.   查找A上的所有非final 的public类型的方法定义，final、static、private修饰符的方法不能被子类覆盖，所以不能代理
 2.   将这些方法的定义转换成字节码；
 3.   将组成的字节码转换成相应的代理的class对象；
 4.   实现 MethodInterceptor接口，用来处理对代理类上所有方法的请求（这个接口和JDK动态代理InvocationHandler的功能和角色是一样的）

 -----------------------------------------------------------------------------------------------------------------------------------------
 jdk动态代理创建时效率比cglib高，但执行效率比cglib低。所以如果代理对象是单例模式，选择cglib动态代理；
 如果是prototype模式，选择jdk动态代理。当遇到jdk和cglib代理局限性无法解决问题时，可以选择Load Time Weaving代理，即aspectJ。

 */
public class CglibMain {

    public static void main(String[] args) {
        CglibProgrammer programmer = new CglibProgrammer();
        Hacker hacker = new Hacker();
        //cglib中的加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(programmer.getClass());
        //设置回调，相当于对代理类上所有非final的public方法的调用，都会调用callback，而callback都需要走intercept()进行拦截
        enhancer.setCallback(hacker);
        //默认是true，如果设置为false，就可以不对构造方法进行拦截
        enhancer.setInterceptDuringConstruction(false);
        CglibProgrammer o = (CglibProgrammer)enhancer.create();
        o.code();
        //o.code1();

    }

}
