package com.liu.j2setest.compare;

/**
 * Created by liuzhilei on 2016/12/29.
 */
public class MainTest {

    public static void main(String[] args) {
        Person p1 = new Person(1, "name1", 20);
        Person p2 = new Person(2, "name2", 19);

        System.out.println("实现comparable接口，按年龄比较---");
        System.out.println(p1.compareTo(p2));

        System.out.println("实现comparator接口，策略模式，按学号比较---");
        PersonComparator personComparator = new PersonComparator();
        System.out.println(personComparator.compare(p1, p2));

    }

}
