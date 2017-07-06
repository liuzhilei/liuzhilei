package com.liu.j2setest.mianshiTest;

/**
 * Created by liuzhilei on 2017/7/6.
 *
 * 父类和子类，对于代码块，静态代码块，初始化方法的输出顺序
 */
public class Test2 {
    {
        System.out.println("父类 代码块");
    }

    static {
        System.out.println("父类 静态代码块");
    }

    public Test2() {
        System.out.println("父类 初始化");
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
    }
}

class SubTest2 extends Test2{

    {
        System.out.println("子类 代码块");
    }

    static {
        System.out.println("子类 静态代码块");
    }

    public SubTest2(){
        System.out.println("子类 初始化");
    }

    public static void main(String[] args) {
        /**
         * 在子类中new父类的实例 执行顺序：
         *  父类 静态代码块
            子类 静态代码块
            父类 代码块
            父类 初始化
         */
        //Test2 test2 = new Test2();

        /**
         * 子类中new子类实例，执行顺序
         * 父类静态代码块
           子类 静态代码块
            父类代码块
            父类 初始化
            子类 代码块
            子类 初始化
         */
        SubTest2 subTest2 = new SubTest2();
    }
}

