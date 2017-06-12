package com.liu.j2setest.designpattern.享元模式;

/**
 * Created by liuzhilei on 2017/6/12.
 * 抽象享元类
 */
public interface ChessFlyWeight {

    //内部状态，获取颜色
    String getColor();

    //外部状态，展示位置
    void display(Coordinate c);
}
