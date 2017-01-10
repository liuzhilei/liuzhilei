package com.liu.j2setest.compare;

import java.util.Comparator;

/**
 * Created by liuzhilei on 2016/12/29.
 * 比较comparable和comparator的区别
 * Comparable在java.lang包下，类直接实现，实现compareTo方法即可
 * comparator在java.util下，需要重新写一个类实现comparator，是策略模式，不改变对象本身
 */
public class Person implements Comparable {
    private int num;//学号
    private String name;//姓名
    private int age;//年龄

    public Person(int num, String name, int age) {
        this.name = name;
        this.num = num;
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        return this.age - ((Person) o).getAge();
    }
}

class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int num1 = o1.getNum();
        int num2 = o2.getNum();
        return num1 > num2 ? 1 : (num1 == num2 ? 0 : -1);

    }
}
