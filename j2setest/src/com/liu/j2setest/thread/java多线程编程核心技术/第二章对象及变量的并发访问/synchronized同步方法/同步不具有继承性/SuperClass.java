package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步方法.同步不具有继承性;

/**
 * Created by liuzhilei on 2017/3/17.
 * 同步是不具有继承性的，父类为synchronized方法，子类重写成普通方法，那么子类的这个方法不会继承父类的同步性
 * <p/>
 * 下面的方法，子类如果是普通方法，那么输出结果会交叉出现。
 */
public class SuperClass {

    synchronized public void method() {
        System.out.println("父类 start 当前线程：" + Thread.currentThread().getName() + ":" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父类 end 当前线程：" + Thread.currentThread().getName() + ":" + System.currentTimeMillis());

    }
}

class ChildClass extends SuperClass {
    @Override
    public synchronized void method() {
        System.out.println("子类 start 当前线程：" + Thread.currentThread().getName() + ":" + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子类 end 当前线程：" + Thread.currentThread().getName() + ":" + System.currentTimeMillis());

        super.method();
    }
}

class ThreadA extends Thread {
    private ChildClass childClass;

    public ThreadA(ChildClass childClass) {
        this.childClass = childClass;
    }

    @Override
    public void run() {
        super.run();
        childClass.method();
    }
}

class ThreadB extends Thread {
    private ChildClass childClass;

    public ThreadB(ChildClass childClass) {
        this.childClass = childClass;
    }

    @Override
    public void run() {
        super.run();
        childClass.method();
    }
}

class Main {
    public static void main(String[] args) {
        ChildClass childClass = new ChildClass();
        Thread thread = new ThreadA(childClass);
        thread.setName("A");
        Thread thread1 = new ThreadB(childClass);
        thread1.setName("B");
        thread.start();
        thread1.start();
    }
}



