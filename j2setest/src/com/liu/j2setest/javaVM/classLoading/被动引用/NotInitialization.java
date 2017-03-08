
package com.liu.j2setest.javaVM.classLoading.被动引用;

/**
 * Created by liuzhilei on 2017/3/7.
 * 先看jvm.docx文档，这里说的是被动引用，也就是调用类中的属性的时候，不初始化这个类的情况的一些例子
 */
public class NotInitialization {
    public static void main(String[] args) {

        /**
         * 被动引用1：子类直接调用父类的静态字段，不会导致子类初始化
         * 只有直接定义静态变量的类，才会被初始化
         */
        System.out.println(SubClass.value);

        /**
         * 被动引用2：通过数组定义来引用类，不会触发该类的初始化。数组本身不通过类加载器创建，它由java虚拟机直接创建
         */
       SuperClass [] su = new SuperClass[10];

        /**
         * 被动引用3：虽然在java源码引用到了ConstantClass中的str常量，但是在编译阶段通过常量传播优化，已经将hello存储到了该类的常量池中
         * 所以以后再调用str时，实际上都被转化成了引用该类对自身常量池中str的引用，必然不会触发ConstantClass的初始化
         */
        System.out.println(ConstantClass.str);
    }
}
