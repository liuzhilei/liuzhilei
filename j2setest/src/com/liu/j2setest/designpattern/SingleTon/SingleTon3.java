package com.liu.j2setest.designpattern.SingleTon;

/**
 * Created by liuzhilei on 2016/12/18.
 * 双重校验链  就是double-check
 */
public class SingleTon3 {
    private static volatile SingleTon3 singleTon3;

    private SingleTon3(){}

    public SingleTon3 getInstance(){

        if(singleTon3 == null){
            synchronized (SingleTon3.class){
                if(singleTon3 == null){
                    singleTon3 = new SingleTon3();
                }
            }
        }
        return singleTon3;
    }

    /*
     *  synchronized保证原子性，有序性，可见性，但是不能禁止指令重排序，volatile可以禁止指令重排序。
     *  有序性体现在在本线程内有序，线程外表现为无需，所以和禁止指令重排序是两码事，所以singleTon3必须加上volatile关键字
     *
     *
     * 变量singleTon3必须有volatile注释的原因：禁止指令重排序。
     * singleTon3 = new SingleTon3();
     * 对于上面的命令，正常的执行逻辑为：
     * 1.jvm为对象分配一块内存M
     * 2.在M上将对象进行初始化
     * 3.将初始化后的对象赋值给singleTon3变量
     *
     * 但是如果发生了执行重排序，可能的步骤为：
     * 1.jvm为对象分配一块内存M
     * 2.将内存M赋值给singleTon3变量
     * 3.在M上将对象进行初始化
     *
     * 不加volatile，比如有如下场景：
     * 1.thread1执行到17行为对象初始化
     * 2.thread2执行到14行 判断singleTon3为空
     * 3.thread2执行到16行 判断singleTon3不为空
     * 4.thread2执行到21行，返回singleTon3，然后调用该类的其他方法，因为可能发生上面的指令重排序，所以就导致了空指针
     */

}
