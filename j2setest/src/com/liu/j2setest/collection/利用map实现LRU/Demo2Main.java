package com.liu.j2setest.collection.利用map实现LRU;

/**
 * Created by liuzhilei3 on 2018/8/19.
 */
public class Demo2Main {

    public static void main(String[] args) {
        LRUCacheDemo2 demo = new LRUCacheDemo2(3);

        demo.set("1",1);
        demo.set("2",2);
        demo.set("3",3);

        demo.set("4",4);

        System.out.println(demo.get("1"));
        System.out.println(demo.get("2"));

        demo.set("5",5);
        System.out.println(demo.get("3"));

    }
}
