package com.liu.j2setest.designpattern.享元模式;

/**
 * Created by liuzhilei on 2017/6/12.
 * 具体享元类，为内部状态提供成员变量进行存储
 */
public class ConcreteChess implements ChessFlyWeight {
    private String color;

    public ConcreteChess(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void display(Coordinate c) {
        System.out.println("棋子颜色" + color);
        System.out.println("棋子位置" + c.getX() + "----" + c.getY());
    }
}
