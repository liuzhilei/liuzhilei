package com.liu.j2setest.javaVM.classLoading;

import java.io.InputStream;

/**
 * Created by liuzhilei on 2017/3/7.
 * 自定义类加载器
 * 类加载器与instanceOf关键字演示
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String stringName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream resourceAsStream = getClass().getResourceAsStream(stringName);
                    if (null == resourceAsStream) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[resourceAsStream.available()];
                    resourceAsStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        };

        Object o = classLoader.loadClass("com.liu.j2setest.javaVM.classLoading.ClassLoaderTest").newInstance();
        System.out.println(o.getClass());
        System.out.println(o instanceof com.liu.j2setest.javaVM.classLoading.ClassLoaderTest);

        /**
         * 输出结果为：
         * class com.liu.j2setest.javaVM.classLoading.ClassLoaderTest
         * false
            第一行输出表示这个对象确实是ClassLoaderTest实例化的类。
            第二行输出false表示实例化的类和当前类不是同一个类，因
         为在虚拟机中存在两个ClassLoaderTest类，一个是由系统类加
         载器生成，另一个是我们利用自定义的类加载器生成，明显不是同一个类

         */

    }
}
