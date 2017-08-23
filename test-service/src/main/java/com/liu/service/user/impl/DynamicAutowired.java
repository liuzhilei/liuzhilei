package com.liu.service.user.impl;

/**
 * Created by liuzhilei on 2017/8/23.
 * 测试程序运行期间，添加 配置文件，能都动态修改属性
 */
public class DynamicAutowired {

    private String userName;

    private String password;

    public void outPut() {
        System.out.println("username ====== " + userName);

        System.out.println("password ====== " + password);
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
