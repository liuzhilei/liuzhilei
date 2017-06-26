package com.liu;

import org.springframework.util.Assert;

/**
 * Created by liuzhilei on 2017/6/26.
 * spring中Assert简单使用
 */
public class Main {
    public static void main(String[] args) {
        Object o = null;

        Assert.notNull(o,"object 是空");

        boolean b = false;
        Assert.isTrue(b,"b是空");


    }
}
