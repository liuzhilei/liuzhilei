package com.liu.system;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by liuzhilei on 2017/2/6.
 * 查看通过System属性得到的所有属性
 */

public class SystemTest {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }

        System.out.println(properties.getProperty("java.version"));
    }
}
