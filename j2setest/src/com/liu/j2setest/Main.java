package com.liu.j2setest;

import com.liu.j2setest.序列化.Person;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BlockingQueue;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    private static final Object myLock = new Object();

    public static void main(String[] args) {

        System.out.println(args[0]);
        System.out.println(args[1]);

        String string = "";
        System.out.println(Runtime.getRuntime().availableProcessors());

        List<Person> list1 = new ArrayList<Person>();
        List<Person> list2 = new ArrayList<Person>();

        Person person = new Person();
        person.setAge(1);
        person.setName("123");
        person.setNo(1);

        Person person1 = new Person();
        person1.setAge(1);
        person1.setName("123");
        person1.setNo(2);

        Person person3 = new Person();
        person3.setAge(3);
        person3.setName("3");
        person3.setNo(3);

        Person person4 = new Person();
        person4.setAge(4);
        person4.setName("4");
        person4.setNo(4);

        Person person5 = new Person();
        person5.setAge(5);
        person5.setName("5");
        person5.setNo(5);


        list1.add(person);
        list1.add(person1);
        list1.add(person4);
        list1.add(person5);
        list1.add(person3);


        list2.add(person1);
        list2.add(person);
        list2.add(person5);
        list2.add(person4);
        list2.add(person3);


        System.out.println(list1.equals(list2));

        System.out.println(person.equals(person1));


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

}
