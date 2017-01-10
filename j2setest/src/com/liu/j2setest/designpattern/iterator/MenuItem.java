package com.liu.j2setest.designpattern.iterator;

/**
 * Created by liuzhilei on 2017/1/4.
 * 菜单项
 */
public class MenuItem {
    String name;
    String description;
    boolean vegetarian;//素食者
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }
}


