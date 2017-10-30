package com.liu.j2setest;

import com.alibaba.fastjson.JSON;
import com.liu.j2setest.compare.Person;
import com.liu.j2setest.reflect.demo4.User;
import com.liu.j2setest.serializable.serializableUtilTest.FastJsonTest;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    private static final Object myLock = new Object();

    public static void main(String[] args) {

        //transfer();
        String str = "2000-00-00 00:00:00";

        //Date date1 = new Date("2000-01-01 00:00:00");
        //System.out.println(date1);

        Date date = StrToDate("2000-01-01 00:00:00");
        Date date1 = StrToDate("0000-00-00 00:00:00");
        System.out.println(date);

        System.out.println(date1);

    }

    public static List<User> getUser() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 11000; i++) {
            User user = new User();
            user.setAge(i);
            list.add(user);
        }
        return list;
    }


    public void test() {
        try {
            //打开一个socketChannel链接
            ServerSocketChannel socketChannel = ServerSocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainTest() {
        System.out.println("static静态导包");
    }

    private static void allocateElements(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        System.out.println("initialCapacity = " + initialCapacity);
    }

    public static void mapTest() {
        Map<String, String> map = new HashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Set<String> strings = map.keySet();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


    public static void chuguannew() {
        for (int i = 1; i <= 160; i++) {
            String str = "ALTER TABLE `jd_chuguan_transfer_" + i + "` ADD INDEX idx_createdate(`creatdate`);";
            System.out.println(str);
        }
    }

    public static void qingdan() {
        for (int i = 1; i <= 160; i++) {
            String str = "ALTER TABLE `jd_qingdan_" + i + "` ADD INDEX idx_createdate(`creatdate`);";
            System.out.println(str);
        }
    }

    public static void transfer() {
        for (int i = 1; i <= 32; i++) {
            String str = "ALTER TABLE `jd_chuguan_transfer_" + i + "` ADD INDEX idx_yuandanhao(`yuandanhao`);";
            System.out.println(str);
        }
    }

    /**
     * 日期转换成字符串
     * @param date
     * @return str
     */
    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
