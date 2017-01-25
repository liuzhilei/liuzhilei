package com.liu.j2setest.effectivejava.createdestoryobject.one;

/**
 * Created by liuzhilei on 2017/1/25.
 */
public class Test {
    public static void main(String[] args) {
        BuildPattern buildPattern = new BuildPattern.Build("刘志磊").buildAge(25).buildCompany("京东").buildSex("男").build();
        System.out.println(buildPattern);
    }
}
