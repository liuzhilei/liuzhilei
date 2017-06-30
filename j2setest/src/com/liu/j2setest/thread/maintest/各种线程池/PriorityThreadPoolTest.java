package com.liu.j2setest.thread.maintest.各种线程池;

import java.util.concurrent.*;

/**
 * Created by liuzhilei on 2017/6/30.
 * 带有优先级的线程池
 * <p/>
 * 要想实现优先级，那么加入到队列中的对象需要实现Comparable接口，omparable要比较的是加入到队列中的对象
 * 可以把业务参数传递进去，就可以确定优先级
 */
public class PriorityThreadPoolTest {


    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor service = new ThreadPoolExecutor(1, 1, 30L, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>(1));
        service.execute(new PriorityThread(1));
        service.execute(new PriorityThread(2));
        service.execute(new PriorityThread(3));
        service.execute(new PriorityThread(4));
        service.execute(new PriorityThread(5));

        service.shutdown();
    }

    static class PriorityThread implements Runnable, Comparable<PriorityThread> {

        private int priority;

        public PriorityThread(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            System.out.println(this.priority);
        }

        @Override
        public int compareTo(PriorityThread priorityThread) {
            int i = this.priority - priorityThread.getPriority();
            //System.out.println(i);
            return i;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }
}
