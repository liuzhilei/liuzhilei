package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步方法.脏读;

/**
 * Created by liuzhilei on 2017/3/17.
 * 脏读
 * 针对同一个对象，setValue为synchronized同步方法，getValue为普通方法
 * 数据会交叉出现，发生脏读
 */
public class DirtyReadTest {
    private String userName = "liuzhilei";
    private String sex = "男";

    synchronized public void setValue(String userName, String sex) {
        this.userName = userName;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.sex = sex;
        System.out.println("setValue 方法，当前线程：" + Thread.currentThread().getName() + ", userName=" + userName + ", sex=" + sex);
    }

    public void getValue() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getValue方法，当前线程：" + Thread.currentThread().getName() + ", userName=" + userName + ", sex=" + sex);
    }

}

class DirtyReadThread extends Thread {
    private DirtyReadTest dirtyReadTest;

    public DirtyReadThread(DirtyReadTest dirtyReadTest) {
        this.dirtyReadTest = dirtyReadTest;
    }

    @Override
    public void run() {
        dirtyReadTest.setValue("金晶晶", "女");
    }
}

class DirtyReadMain {
    public static void main(String[] args) {
        DirtyReadTest dirtyReadTest = new DirtyReadTest();

        Thread thread = new DirtyReadThread(dirtyReadTest);
        thread.start();
        dirtyReadTest.getValue();
    }
}
