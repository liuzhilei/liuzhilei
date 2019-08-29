package com.liu.j2setest;

import com.alibaba.fastjson.JSON;
import com.liu.j2setest.compare.Person;
import com.liu.j2setest.reflect.demo4.User;
import com.liu.j2setest.serializable.serializableUtilTest.FastJsonTest;
import com.liu.j2setest.thread.maintest.多线程异常捕获.线程池情况.ThreadPool;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        for(String string : list){
            System.out.println(string);
        }

    }


    public static void mainTest() {
        System.out.println("static静态导包");
    }


}
