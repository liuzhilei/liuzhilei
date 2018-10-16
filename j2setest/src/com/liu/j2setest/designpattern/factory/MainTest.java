package com.liu.j2setest.designpattern.factory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 */
public class MainTest {
    public static void main(String[] args) {
        MainTest test = new MainTest();
        Animal animal = test.getAnimal(1);
        animal.eatFood();
    }

    /*
      不用工厂模式， 每次加一个动物，都需要添加一个if判断来返回特定的实体，都需要动业务实体
     */
    public Animal getAnimal(int type){
        if(type == 1){
            return new Cat();
        } else if(type == 2){
            return new Dog();
        }
        return null;
    }
}
