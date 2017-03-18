package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块.内置类与静态内置类;

/**
 * Created by liuzhilei on 2017/3/18.
 * 静态内置类
 * 直接new内部类即可，不需要获得外部类的实例
 */
public class Test7 {
    private static String userName;
    private static String password;

    static class InnerTest7 {
        private String sex;
        private String address;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void print() {
            System.out.println(userName + " " + password);
        }

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Test7Main {
    public static void main(String[] args) {
        System.out.println("静态内部类");
        Test7 test7 = new Test7();
        test7.setUserName("userNameValue");
        test7.setPassword("passwordValue");
        System.out.println("外部类：" + test7.getUserName() + ", " + test7.getPassword());

        Test7.InnerTest7 innerTest7 = new Test7.InnerTest7();
        innerTest7.setSex("sexValue");
        innerTest7.setAddress("addressValue");
        System.out.println("静态内部类：" + innerTest7.getSex() + ", " + innerTest7.getAddress());
        innerTest7.print();

    }
}
