package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.等待通知机制;

/**
 * Created by liuzhilei on 2017/6/29.
 * <p/>
 * synchronized解析为二进制字节码后包含指令集monitorenter和monitorexit，执行完
 * monitorenter后就拿到了monitor对象，其实是ObjectMonitor对象
 * <p/>
 * 执行object.wait()必须拿到object的monitor对象，所以必须在synchronized代码块中执行
 * ObjectMonitor对象包含wait_set队列，调用object的wait方法，就把当前线程放入了wait_set
 * 队列中，然后当前线程释放对象锁。
 * <p/>
 * 另一线程拿到该对象锁以后，执行notify方法后，会从wait_set队列中拿到一个线程，然后准备释放该线程。
 * 执行完notify后，当前线程不会立即释放锁，而是执行完monitorexit指令，也就是执行完synchronized代码块
 * 以后才会释放该锁
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程A等待获得锁");
                synchronized (lock) {
                    try {
                        System.out.println("线程A拿到了锁");
                        Thread.sleep(3000);
                        System.out.println("线程A准备执行wait方法");
                        lock.wait();
                        System.out.println("执行wait方法结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程B等待获得锁");
                synchronized (lock) {
                    try {
                        System.out.println("线程B拿到了锁");
                        Thread.sleep(1000);
                        System.out.println("线程B准备执行notify方法");
                        lock.notify();
                        System.out.println("执行notify方法结束，此时不会立即释放锁，需要执行完synchronized代码块");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
