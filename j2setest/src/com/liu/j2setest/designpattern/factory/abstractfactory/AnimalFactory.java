package com.liu.j2setest.designpattern.factory.abstractfactory;

/**
 * Created by liuzhilei3 on 2018/10/16.
 * 抽象工厂，定义一个产品族，比如这个例子中猫和狗两种动物，又分为黑色和白色，后期添加一个花色的，添加一个类也可以
 */
public interface AnimalFactory {
    /**
     * 养狗
     * @return
     */
    IDog keepDog();

    /**
     * 养猫
     * @return
     */
    ICat keepCat();
}
