package com.liu.j2setest.javaVM.java的关闭钩子;

/**
 * Created by liuzhilei on 2017/6/15.
 * Runtime.getRuntime().addShutdownHook(new Thread())
 * 这个方法的意思是在jvm中添加一个关闭的钩子，当jvm关闭之前，会执行系统中通过addShutdownHook设置的所有钩子，然后再关闭jvm
 * 虚拟机，所以在钩子函数里面可以设置内存清理，对象销毁等操作。
 */
public class Demo {

    public static void start() {
        System.out.println("jvm启动");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("关闭后的操作");
            }
        });

    }

    public static void main(String[] args) {
        start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    * tomcat中的Catalina类中有一个钩子内部类的定义：
    *
    protected class CatalinaShutdownHook extends Thread {
        public void run(){
            try {
                if (getServer() != null) {
                    Catalina.this.stop(); // 因为是Catalina的内部类，所以可以调用Catalina的内部方法，此处调用的stop,在tomcat关闭之前，就进行了相应的操作
                }
            } catch (Throwable ex) {
                log.error(sm.getString("catalina.shutdownHookFail"), ex);
            } finally {
                // If JULI is used, shut JULI down *after* the server shuts down
                // so log messages aren't lost
                LogManager logManager = LogManager.getLogManager();
                if (logManager instanceof ClassLoaderLogManager) {
                    ((ClassLoaderLogManager) logManager).shutdown();
                }
            }
        }
    }

    //tomcat在启动的时候会调用Catalina类中的start方法，所以钩子在tomcat启动的时候就被注册到了jvm虚拟机

    if(useShutdownHook)

    {
        if (shutdownHook == null) {
            shutdownHook = new CatalinaShutdownHook();
        }
        Runtime.getRuntime().addShutdownHook(shutdownHook);

    }


    */


}