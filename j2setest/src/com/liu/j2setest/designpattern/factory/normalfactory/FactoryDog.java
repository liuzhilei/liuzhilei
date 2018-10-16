package com.liu.j2setest.designpattern.factory.normalfactory;

import com.liu.j2setest.designpattern.factory.Animal;
import com.liu.j2setest.designpattern.factory.Dog;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class FactoryDog implements NormalFactory {
    @Override
    public Animal getAnimal() {
        return new Dog();
    }
}
