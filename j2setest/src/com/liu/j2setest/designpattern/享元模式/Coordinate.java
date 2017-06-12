package com.liu.j2setest.designpattern.享元模式;

/**
 * Created by liuzhilei on 2017/6/12.
 * 非共享的享元类：不能被共享的子类可以设计为非共享享元类
 * <p/>
 * 外部状态：棋子坐标位置
 */
public class Coordinate {
    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
