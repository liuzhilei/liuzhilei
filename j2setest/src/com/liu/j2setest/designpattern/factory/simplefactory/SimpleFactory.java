package com.liu.j2setest.designpattern.factory.simplefactory;

import com.liu.j2setest.designpattern.factory.Animal;
import com.liu.j2setest.designpattern.factory.Cat;
import com.liu.j2setest.designpattern.factory.Dog;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 简单工厂，缺点是如果新增了一个动物，就需要修改该类，违反了开闭原则
 */
public class SimpleFactory {
    public static Animal getAnimal(String animalName) {
        if (animalName.equals("cat")) {
            return new Cat();
        } else if (animalName.equals("dog")) {
            return new Dog();
        } else {
            return null;
        }
    }
}
