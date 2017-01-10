package com.liu.j2setest.liu;

/**
 * Created by liuzhilei on 2016/9/29.
 */
public class GCTest {

    public static void main(String[] args) {
        new GCTest();
        System.gc();
        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.print("释放资源");
    }

    public static void tt(User user,Callback callback){

    }
}
