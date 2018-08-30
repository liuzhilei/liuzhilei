package com.liu.j2setest.mianshiTest;

import java.util.List;

/**
 * Created by liuzhilei3 on 2018/8/30.
 * 虚假唤醒
 *
 * 场景：三个线程ABC，线程A添加list，线程BC删除list元素。假设线程B先执行，在object.wait()释放了锁，放入waitSet队列中。
 * 然后线程A执行，执行完list.add()；此时线程C执行，运行到同步语句块阻塞。然后线程A执行notify释放锁，线程C拿到了锁，判断list.size>0，执行了remove操作。
 * 对于线程B，如果用if而不是while，那么在B拿到锁以后，会立即执行remove操作，因为list已经为空，所以会报错，造成了虚假唤醒。用while，会再次判断当前状态，不会报错
 */
public class xujiahuanxing {
}

//新增list
class PushThread extends Thread {
    private List<Integer> list;
    private Object object;

    public PushThread(List<Integer> list, Object object) {
        this.list = list;
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            list.add(1);
            object.notify();
        }
    }
}

//删除list元素
class PopThread extends Thread {
    private List<Integer> list;
    private Object object;

    public PopThread(List<Integer> list, Object object) {
        this.list = list;
        this.object = object;
    }

    @Override
    public void run() {
        try {
            synchronized (object) {
                //此处必须为while，不能为if。防止虚假唤醒
                while (list.size() <= 0) {
                    object.wait();
                }
                list.remove(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
