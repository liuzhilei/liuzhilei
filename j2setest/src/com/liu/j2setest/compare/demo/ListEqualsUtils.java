package com.liu.j2setest.compare.demo;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * Created by liuzhilei on 2017/9/3.
 * 判断两个非空list，存放元素顺序不相等的情况下是否相等，经过测试，比sort要浪费时间
 */
public class ListEqualsUtils {

    public static boolean isEquals(Collection c1, Collection c2) {
        if (CollectionUtils.isEmpty(c1) || CollectionUtils.isEmpty(c2)) {
            //添加日志
            return false;
        }
        //判断集合的元素数
        if (c1.size() != c2.size()) {
            return false;
        }

        //转map
        Map<Object, Integer> c1Map = collectionToMap(c1);
        Map<Object, Integer> c2Map = collectionToMap(c2);

        if (c1Map.size() != c2Map.size()) {
            return false;
        }

        //遍历map的key，进行value值的比较
        Iterator keys = c1Map.keySet().iterator();
        while (keys.hasNext()) {
            Object obj = keys.next();
            if (valueCount(obj, c1Map) != valueCount(obj, c2Map)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将集合转为map，key为集合的元素，value为出现的次数
     *
     * @param c1
     * @return
     */
    private static Map<Object, Integer> collectionToMap(Collection c1) {
        Map<Object, Integer> map = new HashMap<Object, Integer>();
        Iterator iterator = c1.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            Integer count = map.get(obj);
            if (count == null) {
                map.put(obj, 1);
            } else {
                map.put(obj, ++count);
            }
        }
        return map;
    }

    /**
     * obj对应的value，即obj在集合中出现的次数
     *
     * @param obj
     * @param map
     * @return
     */
    private static int valueCount(Object obj, Map<Object, Integer> map) {
        Integer count = map.get(obj);
        if (count != null) {
            return count.intValue();
        }
        return 0;
    }


    public static void main(String[] args) {
        List list = null;
        System.out.println(isEquals(list, list));

        Integer i = 1;

    }

}
