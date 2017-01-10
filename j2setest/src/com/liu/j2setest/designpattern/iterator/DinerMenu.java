package com.liu.j2setest.designpattern.iterator;

import java.util.Iterator;

/**
 * Created by liuzhilei on 2017/1/4.
 * 符合菜单规定的餐厅项
 */
public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("餐厅菜品1", "餐厅的第一种菜品", true, 50);
        addItem("餐厅菜品2", "餐厅的第二种菜品", false, 60);
        addItem("餐厅菜品3", "餐厅的第三种菜品", true, 70);
        addItem("餐厅菜品4", "餐厅的第四种菜品", false, 80);

    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("菜单已满，不能再添加");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
