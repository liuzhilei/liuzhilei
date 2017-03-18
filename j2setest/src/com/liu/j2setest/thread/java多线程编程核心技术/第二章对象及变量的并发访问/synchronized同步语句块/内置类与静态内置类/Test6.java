package com.liu.j2setest.thread.java多线程编程核心技术.第二章对象及变量的并发访问.synchronized同步语句块.内置类与静态内置类;

/**
 * Created by liuzhilei on 2017/3/18.
 * <p/>
 * 内置类的例子
 * 先实例化外部类，才能实例化内部类
 */
public class Test6 {
    private String userName;
    private String password;

    class InnerTest6 {
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
            System.out.println("userName : " + userName + " , password : " + password);
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

class Test6Main {
    public static void main(String[] args) {
        System.out.println("内部类");
        Test6 test6 = new Test6();
        test6.setUserName("usernameValue");
        test6.setPassword("passwordValue");
        Test6.InnerTest6 innerTest6 = test6.new InnerTest6();
        System.out.println("外部类：" + test6.getUserName() + "," + test6.getPassword());
        innerTest6.setSex("sexValue");
        innerTest6.setAddress("addressValue");
        System.out.println("内部类：" + innerTest6.getSex() + "," + innerTest6.getAddress());
        innerTest6.print();
    }
}
