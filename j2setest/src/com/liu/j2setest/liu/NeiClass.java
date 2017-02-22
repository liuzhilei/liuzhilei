package com.liu.j2setest.liu;

/**
 * Created by liuzhilei on 2016/10/8.
 */
public class NeiClass {

    public void myTest(final User user){
        final String sex = "男";
        GCTest.tt(user, new Callback() {
            @Override
            public int callback(int i) {
                user.getUserName();
                user.setUserSex(sex);
                return 0;
            }
        });
        /*final String name = "123";
        class My{
            public void tt(){
                User u = new User();
                u.setUserName(name);
            }
        }*/

    }
}
