package com.liu.j2setest.compare.demo;

import com.liu.j2setest.IO.NIO.Demo;

import java.util.*;

/**
 * Created by liuzhilei on 2017/9/3.
 *
 * 两个集合相比较
 * 1.可以把两个都放入map中，根据相同的key，然后比较value
 * 2.实现Comparable，重写比较器，利用集合的sort先排序，然后再对比
 * 3.对应实体重写hashcode和equals，将list集合直接转换成set，调用set的equals进行比较
 * 效率：set>sort>转换成map遍历
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
        for (int i = 0; i < 5000000; i++) {
            list.add(v1);
        }

        List<DemoVo> list1 = new ArrayList();
        for (int i = 0; i < 5000000; i++) {
            list1.add(v4);
        }


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

    }
}
