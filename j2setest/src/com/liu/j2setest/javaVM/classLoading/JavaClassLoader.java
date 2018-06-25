package com.liu.j2setest.javaVM.classLoading;

import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by liuzhilei3 on 2018/6/25.
 * java中的classLoader
 */
public class JavaClassLoader {
    public static void main(String[] args) {

        /**
         * BootstrapClassLoader是用c语言编写的
         * 获取BootstrapClassLoader加载的路径：
         */
        System.out.println("======BootstrapClassLoader======");
        //方法一：
        String bootProperty = System.getProperty("java.class.path");
        System.out.println(bootProperty);

        //方法二：
        URL[] bootUrLs = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < bootUrLs.length; i++) {
            System.out.println(bootUrLs[i]);
        }

        //方法三：通过-Xbootclasspath指定


        /**
         * ExtClassLoader继承自URLClassLoader
         * 获取ExtClassLoader加载的路径：
         */
        System.out.println("======ExtClassLoader======");
        //方法一：
        String extProperty = System.getProperty("java.ext.dirs");
        System.out.println(extProperty);

        //方法二：
        URL[] extUrLs1 = ((URLClassLoader) JavaClassLoader.class.getClassLoader().getParent()).getURLs();
        for (int i = 0; i < extUrLs1.length; i++) {
            System.out.println(extUrLs1[i]);
        }

        //方法三：通过-Djava.ext.dirs指定


        /**
         * AppClassLoader继承自URLClassLoader
         * 获取AppClassLoader加载的路径：
         */
        System.out.println("======AppClassLoader======");
        //方法一：
        String appProperty = System.getProperty("sun.boot.class.path");
        System.out.println(appProperty);

        //方法二：
        URL[] appUrLs = ((URLClassLoader) JavaClassLoader.class.getClassLoader()).getURLs();
        for (int i = 0; i < appUrLs.length; i++) {
            System.out.println(appUrLs[i]);
        }


    }

}
