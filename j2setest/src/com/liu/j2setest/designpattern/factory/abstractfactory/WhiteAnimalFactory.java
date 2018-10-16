package com.liu.j2setest.designpattern.factory.abstractfactory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class WhiteAnimalFactory implements AnimalFactory {
    @Override
    public IDog keepDog() {
        return new WhiteDog();
    }

    @Override
    public ICat keepCat() {
        return new WhiteCat();
    }
}
