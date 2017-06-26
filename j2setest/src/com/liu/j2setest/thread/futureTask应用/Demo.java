package com.liu.j2setest.thread.futureTask应用;

import com.liu.j2setest.thread.maintest.CompletionServiceDemo;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/6/26.
 * {@link java.util.concurrent.FutureTask}实现了{@link java.util.concurrent.RunnableFuture}接口
 * RunnableFuture接口又实现了<p>Runnable</p>和<p>Future<V></p>接口，所以FutureTask既能当作runnable
 * 直接被Thread执行，也可以作为Future用来接收Callable的计算结果
 * <p/>
 * FutureTask一般配合ExecutorService使用，也可以直接通过Thread来使用
 * <p/>
 * 对于实现runnable接口的线程，可以通过FutureTask的构造方法{@link java.util.concurrent.FutureTask#FutureTask(java.lang.Runnable, java.lang.Object)}
 * 指定线程成功执行后的返回值，就可以使用FutureTask.get()方法获取，或者捕获异常
 */
public class Demo {

    public static void main(String[] args) {
        Task task = new Task();
        ExecutorService executorService = Executors.newCachedThreadPool();

        /**
         * 第一种方式：Future+ExecutorService
         */
        /*Future<Integer> future = executorService.submit(task);
        System.out.println(future.get());
        executorService.shutdown();*/

        /**
         * 第二种方式：FutureTask+ExecutorService
         */
        /*FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executorService.submit(futureTask);
        executorService.shutdown();*/

        /**
         * 第三种方式：FutureTask+Thread
         */
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.setName("自定义线程名称");
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!futureTask.isDone()) {
            System.out.println("任务还没有执行完成");
        }

        int result = 0;
        try {
            //没有拿到结果前，get会一直阻塞
            result = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行结果是：" + result);

        /*--------------------------------------------------------------------------------------------------------------------------*/

        /**
         * 对于实现runnable接口的线程，可以通过FutureTask指定返回值的构造方法得到返回值，或者捕获异常
         */
        Task1 task1 = new Task1();
        FutureTask<Integer> task2 = new FutureTask<Integer>(task1, 1);
        Thread thread1 = new Thread(task2);
        thread1.start();
        try {
            System.out.println(task2.get());
        } catch (InterruptedException e) {
            System.out.println("error1");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("error2");
            e.printStackTrace();
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] 正在运行。。。");
            int result = 0;
            for (int i = 0; i < 100; i++) {
                result += i;
            }
            Thread.sleep(3000);
            return result;
        }
    }

    static class Task1 implements Runnable {
        @Override
        public void run() {
            int i = 1 / 0;
            System.out.println(111);
        }
    }
}
