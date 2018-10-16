package com.liu.j2setest.designpattern.factory.abstractfactory;

import com.liu.j2setest.designpattern.factory.Animal;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class BlackAnimalFactory implements AnimalFactory{
    @Override
    public IDog keepDog() {
        return new BlackDog();
    }

    @Override
    public ICat keepCat() {
        return new BlackCat();
    }
}
