package com.liu.j2setest.compare.demo;

import com.liu.j2setest.IO.NIO.Demo;

import java.util.*;

/**
 * Created by liuzhilei on 2017/9/3.
 *
 * 比较总结，如果对于数据量大的集合，先排序再比较是比较快的
 * 如果数据量小的集合，直接转set比较是比较快的
 */
public class DemoMain {
    public static void main(String[] args) {
        DemoVo v1 = new DemoVo("1", 1, 1);
        DemoVo v2 = new DemoVo("2", 2, 1);
        DemoVo v3 = new DemoVo("3", 3, 1);

        DemoVo v4 = new DemoVo("1", 1, 2);
        DemoVo v5 = new DemoVo("2", 2, 2);
        DemoVo v6 = new DemoVo("3", 3, 2);

        System.out.println(v1.equals(v4));
        System.out.println(v2.equals(v5));
        System.out.println(v3.equals(v5));

        List<DemoVo> list = new ArrayList();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        for (int i = 0; i < 10000; i++) {
            list.add(v1);
        }
        list.add(v3);
        list.add(v2);
        /*for (int i = 0; i < 1000000; i++) {
            list.add(v2);
        }*/

        List<DemoVo> list1 = new ArrayList();
        list1.add(v4);
        list1.add(v6);
        list1.add(v5);
        for (int i = 0; i < 10000; i++) {
            list1.add(v4);
        }
        list1.add(v5);
        list1.add(v6);
        /*for (int i = 0; i < 1000000; i++) {
            list1.add(v5);
        }*/


        System.out.println(list.equals(list1));

        long start = System.currentTimeMillis();
        boolean b = ListEqualsUtils.isEquals(list, list1);
        long end = System.currentTimeMillis();
        System.out.println(b);
        System.out.println((end - start) + "ms");


        start = System.currentTimeMillis();
        Collections.sort(list);
        Collections.sort(list1);
        boolean flag = list.equals(list1);
        end = System.currentTimeMillis();
        System.out.println(flag);
        System.out.println((end - start) + "ms");


        start = System.currentTimeMillis();
        Set<DemoVo> set1 = new HashSet<DemoVo>(list);
        Set<DemoVo> set2 = new HashSet<DemoVo>(list1);
        boolean b1 = set1.equals(set2);
        end = System.currentTimeMillis();
        System.out.println(b1);
        System.out.println((end - start) + "ms");

        List list2 = null;
        Set set = new HashSet(list2);

    }
}
