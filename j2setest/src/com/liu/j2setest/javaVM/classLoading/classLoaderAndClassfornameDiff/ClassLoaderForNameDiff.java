package com.liu.j2setest.javaVM.classLoading.classLoaderAndClassfornameDiff;

/**
 * Created by liuzhilei3 on 2018/10/10.
 * classLoader.loader与class.forName的区别
 */
public class ClassLoaderForNameDiff {
    public static void main(String[] args) throws Exception{
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };                                                

        //classLoader.loadClass不会执行类的static代码块，只有调用了newInstance才会执行
        Class<?> aClass = classLoader.loadClass("com.liu.j2setest.javaVM.classLoading.classLoaderAndClassfornameDiff.DemoClass");
        //aClass.newInstance();

        //Class.forName会执行类的static代码块
        Class<?> aClass1 = Class.forName("com.liu.j2setest.javaVM.classLoading.classLoaderAndClassfornameDiff.DemoClass");
        //Class<?> aClass1 = Class.forName("com.liu.j2setest.javaVM.classLoading.classLoaderAndClassfornameDiff.DemoClass",false,ClassLoaderForNameDiff.class.getClassLoader());
    }


}
