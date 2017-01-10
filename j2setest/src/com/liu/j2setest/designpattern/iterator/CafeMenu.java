package com.liu.j2setest.designpattern.iterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liuzhilei on 2017/1/4.
 */
public class CafeMenu implements Menu {
    Map menuItems = new HashMap();

    public CafeMenu() {
        addItem("咖啡1", "第一种咖啡", true, 1);
        addItem("咖啡2", "第二种咖啡", false, 2);
        addItem("咖啡3", "第三种咖啡", true, 3);
        addItem("咖啡4", "第四种咖啡", false, 4);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.put(menuItem.getName(), menuItem);
    }

    @Override
    public Iterator createIterator() {
        return menuItems.values().iterator();
    }
}
