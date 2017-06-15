package com.liu.j2setest.collection.集合迭代器快速失败行为;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by liuzhilei on 2017/6/15.
 *
 * 单线程下的迭代器快速失败：
 */
public class Demo1 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        list.add(10);
        while (iterator.hasNext()) {
            iterator.next();
        }

    }
}
