package com.liu.j2setest.collectionstest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/9.
 *
 * shuffle，对list进行重新排序
 */
public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println("before:  " + list.get(0));
        Collections.shuffle(list);
        System.out.println("after:   " + list.get(0));

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("","");
    }
}
