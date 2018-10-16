package com.liu.j2setest.designpattern.factory.simplefactory;

import com.liu.j2setest.designpattern.factory.Animal;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class SimpleMain {
    public static void main(String[] args) {
        Animal animal = SimpleFactory.getAnimal("dog");
        animal.eatFood();
    }
}
