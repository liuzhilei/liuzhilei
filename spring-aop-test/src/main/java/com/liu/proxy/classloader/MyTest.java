package com.liu.proxy.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by liuzhilei on 2017/1/17.
 * class文件加载。
 * 在运行时期可以按照Java虚拟机规范对class文件的组织规则生成对应的二进制字节码。
 * 当前有很多字节码生成开源框架可以完成这些功能，如ASM，Javassist。
 */
public class MyTest {

    public static void main(String[] args) throws Exception {
        //读取Programmer.class的字节码，转换成字节码数组
        File file = new File("D:/IdeaProjects/IdeaProjects10/liuzhilei/spring-aop-test/target/classes/com/liu/proxy/classloader/Programmer.class");
        InputStream is = new FileInputStream(file);
        byte[] b = new byte[1024];
        int count = is.read(b);

        //使用自己定义的类加载器将字节码数组转换成class对象
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> aClass = classLoader.myDefineClass(b, 0, count);
        System.out.println(aClass.getCanonicalName());

        //实例化一个对象,调用其中的code方法
        Object o = aClass.newInstance();
        o.getClass().getMethod("code", null).invoke(o, null);


    }

}
