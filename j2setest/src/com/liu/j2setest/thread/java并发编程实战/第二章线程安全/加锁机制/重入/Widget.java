package com.liu.j2setest.thread.java并发编程实战.第二章线程安全.加锁机制.重入;

/**
 * Created by liuzhilei on 2017/3/13.
 * 重入：内置锁是可以重入的
 * 某个线程如果试图获得自己持有的锁的时候，那么这个请求就会成功。
 * <p/>
 * 下面的例子中，子类重写了父类的synchronized方法，并且调用父类的该方法，此时如果没有可重入的锁，那这段代码将会死锁。
 * 这两个类的doSomething方法都是synchronized方法，在执行前都会获得Widget的锁。所以是线程视图获取自己持有的锁，可以重入，所以不会死锁。
 */
public class Widget {
    public synchronized void doSomething() {
        System.out.println("父类");
    }
}

class subWidget extends Widget {
    public synchronized void doSomething() {
        super.doSomething();
        System.out.println("子类");
        ;
    }

    public static void main(String[] args) {
        new subWidget().doSomething();
    }
}
