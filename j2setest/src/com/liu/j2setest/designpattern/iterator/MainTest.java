package com.liu.j2setest.designpattern.iterator;

/**
 * Created by liuzhilei on 2017/1/4.
 */
public class MainTest {
    //迭代器模式主方法
    public static void main(String[] args) {
        Waitress waitress = new Waitress(new PancakeHouseMenu(),new DinerMenu(),new CafeMenu());
        waitress.printMenu();
    }
}
