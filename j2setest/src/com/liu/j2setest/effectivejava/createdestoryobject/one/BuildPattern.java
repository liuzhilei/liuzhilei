package com.liu.j2setest.effectivejava.createdestoryobject.one;

/**
 * Created by liuzhilei on 2017/1/25.
 * java创建对象的时候，如果定义好多个构造函数，会导致模糊不清到底该调用哪一个。
 * 如果用new对象，然后set属性的方式会太麻烦。
 * 最合适的方法就是应用下面的Build模式
 *
 * 缺点：多余创建了对象
 */
public class BuildPattern {

    private String name;
    private int age;
    private String sex;
    private String company;

    public static class Build{
        private String name;
        private int age = 0;
        private String sex;
        private String company;

        //假设name是必须要传递的，就放在build的构造函数中
        public Build(String name){
            this.name = name;
        }

        public Build buildAge(int age){
            this.age = age;
            return this;
        }

        public Build buildSex(String sex){
            this.sex = sex;
            return this;
        }

        public Build buildCompany(String company){
            this.company = company;
            return this;
        }

        //调用外部的构造函数，返回外部的类
        public BuildPattern build(){
            return new BuildPattern(this);
        }

    }

    private BuildPattern(Build build){
        this.name = build.name;
        this.age = build.age;
        this.sex = build.sex;
        this.company = build.company;
    }

}
