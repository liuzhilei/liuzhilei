package com.liu.j2setest;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    private static final Object myLock = new Object();

    public static void main(String[] args) {

/*        String string = "1,2,liuzhilei,3,4,api.rd";

        String c = "liuzhile";

        System.out.println(string.contains("," + c + ","));


        List list  = Arrays.asList(string.split(","));

        System.out.println(list.get(1));

        System.out.println(list.contains("liuzh"));*/

        /*String sss = "";
        List<String> lll = Arrays.asList(sss.split(","));
        System.out.println(lll.contains("123"));
*/

        //System.out.println(65211 % 10000);

      /*  System.out.println(args[0]);
        System.out.println(args[1]);

        String string = "";
        System.out.println(Runtime.getRuntime().availableProcessors());

        List<Person> list1 = null;
        List<Person> list2 = new ArrayList<Person>();


        Set<Person> set = new HashSet<Person>(list2);
        System.out.println(set.size());*/

/*
        String string = "245445656";
        System.out.println(string.hashCode());

        Map<String, String> map = new HashMap<String, String>();
        System.out.println(map.get("key") == null);*/

        //chuguannew();

        //qingdan();

        transfer();

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
        for (int i = 0; i <= 160; i++) {
            String str = "ALTER TABLE `jd_chuguan_new_" + i + "` ADD INDEX idx_busiNo_typeId(`BusiNo`,`TypeID`);";
            System.out.println(str);
        }
    }

    public static void qingdan() {
        for (int i = 0; i <= 160; i++) {
            String str = "ALTER TABLE `jd_qingdan_" + i + "` ADD INDEX idx_kdanhao(`kdanhao`);";
            System.out.println(str);
        }
    }

    public static void transfer() {
        for (int i = 1; i <= 32; i++) {
            String str = "ALTER TABLE `jd_chuguan_transfer_" + i + "` ADD INDEX idx_kdanhao(`kdanhao`);";
            System.out.println(str);
        }
    }

}
