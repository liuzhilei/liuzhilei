package com.liu.j2setest;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/1/10.
 */
 public class Main {

    private static final Object myLock = new Object();

    public static void main(String[] args) {
        String string = "";
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

    public void test(){
        try {
            //打开一个socketChannel链接
            ServerSocketChannel socketChannel = ServerSocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
