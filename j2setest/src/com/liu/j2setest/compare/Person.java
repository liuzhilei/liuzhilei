package com.liu.j2setest.compare;

import java.util.Comparator;

/**
 * Created by liuzhilei on 2016/12/29.
 * 比较comparable和comparator的区别
 * Comparable在java.lang包下，类直接实现，实现compareTo方法即可
 * comparator在java.util下，需要重新写一个类实现comparator，是策略模式，不改变对象本身
 */
public class Person implements Comparable<Person> {
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

    /**
     * 当前对象 - 比较对象
     * 返回-1 当前对象排在前面
     * 返回1，当前对象排在后面
     * @param o
     * @return
     */
    @Override
    public int compareTo(Person o) {
        int i = this.age - o.getAge();
        System.out.println("当前对象：" + this.age);
        System.out.println("比较的对象：" + o.getAge());
        System.out.println("比较结果" + i);
        if (i > 0) {
            return 1;
        } else if (i < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
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
