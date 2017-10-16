package com.liu.j2setest.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
//利用静态导包
import static com.google.common.collect.Maps.*;

import java.util.Map;

/**
 * Created by liuzhilei on 2017/10/13.
 * guava操作
 */
public class GuavaTest {
    public static void main(String[] args) {
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
}
