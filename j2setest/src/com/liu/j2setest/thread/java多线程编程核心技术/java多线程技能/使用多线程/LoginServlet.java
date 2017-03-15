package com.liu.j2setest.thread.java多线程编程核心技术.java多线程技能.使用多线程;

/**
 * Created by liuzhilei on 2017/3/15.
 * 模拟一个servlet类
 *
 *
 */
public class LoginServlet {
    private static String userNameRef;
    private static String passwordRef;

    /**
     * 注意：
     * 如果该方法不加synchronized 输出结果会错乱
     */
    public synchronized static void doPost(String userName, String password) {
        userNameRef = userName;
        if (userName.equals("a")) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        passwordRef = password;
        System.out.println("userName=" + userNameRef + ",password=" + passwordRef);
    }

}

class AThread extends Thread {
    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("a", "aa");
    }
}

class BThread extends Thread {
    @Override
    public void run() {
        super.run();
        LoginServlet.doPost("b", "bb");
    }
}

class MainTest {
    public static void main(String[] args) {
        Thread t1 = new AThread();
        Thread t2 = new BThread();
        t1.start();
        t2.start();
    }

}
