package com.liu.j2setest.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by liuzhilei on 2016/12/29.
 */
public class MainTest {

    public static void main(String[] args) {
        Person p2 = new Person(2, "name2", 19);
        Person p3 = new Person(3, "name2", 30);
        Person p1 = new Person(1, "name1", 20);



        List<Person> list = new ArrayList<Person>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Collections.sort(list);

        //System.out.println("实现comparable接口，按年龄比较---");
        //System.out.println(p1.compareTo(p2));
        System.out.println(list);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }







        System.out.println("实现comparator接口，策略模式，按学号比较---");
        PersonComparator personComparator = new PersonComparator();
        System.out.println(personComparator.compare(p1, p2));

    }

}
