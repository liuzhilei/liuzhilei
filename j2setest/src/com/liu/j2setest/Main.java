package com.liu.j2setest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liuzhilei on 2017/1/10.
 */
 public class Main {

    public static void main(String[] args) {
        String string = "";
        System.out.println(Runtime.getRuntime().availableProcessors());
    }



}

class Something {
    public synchronized void isSyncA(){}
    public synchronized void isSyncB(){}
    public static synchronized void cSyncA(){}
    public static synchronized void cSyncB(){}
}
