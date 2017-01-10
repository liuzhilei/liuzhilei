package com.liu.j2setest.designpattern.SingleTon;

/**
 * Created by liuzhilei on 2016/12/18.
 */
public class SingleTon3 {
    private static volatile SingleTon3 singleTon3;

    private SingleTon3(){}

    public SingleTon3 getInstance(){

        if(singleTon3 == null){
            synchronized (SingleTon3.class){
                if(singleTon3 == null){
                    singleTon3 = new SingleTon3();
                }
            }
        }
        return singleTon3;
    }

}
