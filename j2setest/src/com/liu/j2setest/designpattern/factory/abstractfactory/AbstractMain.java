package com.liu.j2setest.designpattern.factory.abstractfactory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class AbstractMain {
    public static void main(String[] args) {
        AnimalFactory blackAnimalFactory = new BlackAnimalFactory();
        blackAnimalFactory.keepCat().eatFood();
        blackAnimalFactory.keepDog().eatFood();

        AnimalFactory whiteAnimalFactory = new WhiteAnimalFactory();
        whiteAnimalFactory.keepDog().eatFood();
        whiteAnimalFactory.keepCat().eatFood();
    }
}
