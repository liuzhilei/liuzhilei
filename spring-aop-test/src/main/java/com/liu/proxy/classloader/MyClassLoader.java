package com.liu.proxy.classloader;

/**
 * Created by liuzhilei on 2017/1/17.
 * 自定义一个类加载器，用于将字节码转换成class对象
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> myDefineClass(byte[] b, int off, int len) {
        return super.defineClass(b, off, len);
    }

}
