
package com.liu.j2setest.designpattern.iterator;

import java.util.Iterator;

/**
 * Created by liuzhilei on 2017/1/4.
 */
public class DinerMenuIterator implements Iterator {
    MenuItem[] list;
    int position = 0;

    public DinerMenuIterator(MenuItem[] list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        if (position >= list.length - 1 || list[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public Object next() {
        MenuItem menuItem = list[position];
        position++;
        return menuItem;
    }

    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException("当前位置不能删除");
        }
        if (list[position - 1] != null) {
            for (int i = position - 1; i < list.length - 1; i++) {
                list[i] = list[i + 1];
            }
            list[list.length - 1] = null;
        }

    }
}
