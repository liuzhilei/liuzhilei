package com.liu.j2setest.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
//利用静态导包
import static com.google.common.collect.Maps.*;

import java.util.Map;

/**
 * Created by liuzhilei on 2017/10/13.
 * guava操作
 */
public class GuavaTest {
    public static void main(String[] args) {
        GuavaTest guavaTest = new GuavaTest();
        guavaTest.limit();


    }

    public void operateListMap(){
        //简化map格式
        Map<String, String> map = Maps.newHashMap();
        //利用静态导包
        Map<String, String> map1 = newHashMap();

        /**
         * 操作lists和maps
         */
        //定义不可变的list
        ImmutableList<String> immutableList = ImmutableList.of("1", "2", "3", "4");
        System.out.println(immutableList.get(2));
        //定义不可变的map
        ImmutableMap<String, String> immutableMap = ImmutableMap.of("key1", "value1", "key2", "value2");
        System.out.println(immutableMap.get("key1"));
    }

    /**
     * 利用guava的RateLimiter进行限流
     */
    public void limit() {
        //每秒向桶中放入5个token
        RateLimiter rateLimiter = RateLimiter.create(5);

        /**
         * 大约2s中可以执行完毕
         */
        /*for (int i = 0; i < 10; i++) {
            //从桶中获取1个token
            double waitTime = rateLimiter.acquire();
            System.out.println(waitTime);
        }*/

        /**
         * 这一段，先把桶里面的全部取出，如果直接执行tryAcquire必定返回false，因为桶里面已经空了，需要等桶里面重新有了数据以后才能获取
         */
        System.out.println(rateLimiter.acquire(5));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(rateLimiter.tryAcquire());
    }

}
