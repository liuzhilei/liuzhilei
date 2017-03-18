package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.volatile关键字;

/**
 * Created by liuzhilei on 2017/3/18.
 * <p/>
 * volatile关键字用于线程间变量的共享
 * <p/>
 * 两种方法解决死循环：
 * 1.变量上添加volatile，是变量得到共享
 * 2.while循环中添加synchronized代码块，每次只有一个线程进入while循环中，也可以保证不会死循环
 */
public class Test1 implements Runnable {
    /**
     * 如果不加volatile 关键字，即便running设置成了false，程序依然会进入死循环
     * 因为main方法中设置isRunning为false是设置的公共堆栈中的值，但是线程读取的是线程栈中的数值
     */
    private volatile boolean isRunning = true;


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("线程运行");
        while (isRunning()) {
            /*synchronized (""){

            }*/

        }
        System.out.println("线程终止运行");
    }
}

class Test1Main {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Thread thread = new Thread(test1);
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test1.setRunning(false);
        System.out.println("running已经被复制为false");
    }
}
