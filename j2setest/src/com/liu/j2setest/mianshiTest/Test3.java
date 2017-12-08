package com.liu.j2setest.mianshiTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2017/12/6.
 * 两个list，list1部分包含list，以list为准去除和list1中重复的字段，利用集合的removeAll就可以
 */
public class Test3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);

        List<Integer> list1 = new ArrayList();
        list1.add(2);
        list1.add(3);

        list.removeAll(list1);
        System.out.println(list);
    }
}
