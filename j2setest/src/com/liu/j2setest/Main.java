package com.liu.j2setest;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    private static final Object myLock = new Object();

    public static void main(String[] args) {
        String string = "";
        System.out.println(Runtime.getRuntime().availableProcessors());

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.get("");

        HashMap map = new HashMap();


        ArrayList list = new ArrayList();
        Iterator iterator = list.iterator();


        LinkedList linkedList = new LinkedList();
        linkedList.offer("");
        linkedList.add("");
        //Queue queue = new

        allocateElements(10);

        System.out.println(5 & 3);
        



        System.out.println("基本类型数组："+new int[2] instanceof Object);

    }

    public void test() {
        try {
            //打开一个socketChannel链接
            ServerSocketChannel socketChannel = ServerSocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainTest() {
        System.out.println("static静态导包");
    }

    private static void allocateElements(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        System.out.println("initialCapacity = " + initialCapacity);
    }

}
