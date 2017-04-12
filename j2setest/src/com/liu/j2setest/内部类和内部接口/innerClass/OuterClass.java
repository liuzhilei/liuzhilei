package com.liu.j2setest.内部类和内部接口.innerClass;

/**
 * Created by liuzhilei on 2017/4/12.
 * 内部类
 * 作用：
 * 1.内部类可以实现隐藏
 * 2.内部类有所有外部元素的访问权限
 * 3.最重要的一点，可以实现多重继承
 * 4.一个类继承class1，实现interface1，如果两个类有同名方法，可以用来区分
 */
public class OuterClass extends ExtendsMain{

    private static int i = 0;

    //内部类，实现多继承
    class InnerClass extends Extends1{
        public void innerMethod(){
            i = 1;//第二条
            System.out.println("inner method");
        }
    }

    //静态内部类，实现多继承
    static class StaticInnerClass extends Extends2{
        public void staticInnerMethod(){
            i = 2;
            System.out.println("static inner method");
        }
    }

    //第四条，当父类和接口具有同样的方法名时，可以用于区分调用的具体哪个方法
    public class InnerClass2 implements InterfaceMain{
        @Override
        public void test() {
            System.out.println("实现接口中的test方法");
        }
    }

    @Override
    public void test() {
        super.test();
        System.out.println("重写父类中的test方法");
    }
}

class Demo{
    public static void main(String[] args) {
        //实例化普通内部类，需要实例外部实体
        OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
        innerClass.innerMethod();

        //实例化静态内部类，不需要实例化外部类
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass.StaticInnerClass();
        staticInnerClass.staticInnerMethod();

        OuterClass outerClass = new OuterClass();
        //调用父类test方法
        outerClass.test();
        //实现接口的test方法
        outerClass.new InnerClass2().test();


    }
}
