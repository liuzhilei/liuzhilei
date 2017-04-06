package com.liu.j2setest.thread.maintest;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/3/24.
 * <p/>
 * CompletionService将executor和blockQueen融合到了一起
 * CompletionService是将线程池中所有线程的执行结果future放入blockQueen队列，take可以取出结果
 * 应用：对于多重验证，如果互相之间没有关联，可以应用CompletionService，利用线程池，提高性能,更快的处理返回结果
 */
public class CompletionServiceDemo {

    public static class Task implements Callable<Boolean> {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public Boolean call() throws Exception {
            Thread.sleep(new Random().nextInt(5000));
            System.out.println("ThreadName : " + Thread.currentThread().getName());
            return !(i == 5);
        }
    }

    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService completionService = new ExecutorCompletionService(executorService);
        try {
            for (int i = 0; i < 10; i++) {
                //执行任务，将执行结果future放入了blockQueen队列中，take可以取出
                completionService.submit(new Task(i));
            }

            for (int i = 0; i < 10; i++) {
                //take等待下一个结果并且返回future对象
                //poll不等待，有结果就返回future对象，没有就返回null
                //completionService.poll();
                System.out.println(completionService.take().get() + " i = " + i);//从blockQueen队列中利用take取出结果
            }
            System.out.println("CompletionService 执行完了............");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        CompletionServiceDemo completionServiceDemo = new CompletionServiceDemo();
        completionServiceDemo.run();
    }


}
