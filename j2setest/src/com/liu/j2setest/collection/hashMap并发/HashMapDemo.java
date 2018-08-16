package com.liu.j2setest.collection.hashMap并发;

/**
 * Created by liuzhilei3 on 2018/8/15.
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMapThread test1 = new HashMapThread();
        HashMapThread test2 = new HashMapThread();
        HashMapThread test3 = new HashMapThread();
        HashMapThread test4 = new HashMapThread();
        HashMapThread test5 = new HashMapThread();

        test1.start();
        test2.start();
        test3.start();
        test4.start();
        test5.start();
    }
}
