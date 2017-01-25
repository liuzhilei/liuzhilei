package com.liu.j2setest.effectivejava.createdestoryobject.two;

/**
 * Created by liuzhilei on 2017/1/25.
 * 构造函数中对反射攻击做了拦截，new第二个对象的时候进行拦截
 */
public class Single {

    private static boolean flag = false;
    private static final Single SINGLE = new Single();
    private Single(){
        synchronized (Single.class){
            if(false == flag){
                flag = !flag;
            }else{
                //拦截反射
                System.out.println("单例模式正在被攻击");
                throw  new RuntimeException("attack");
            }
        }
        System.out.println("single");

    }

    public static Single getSingle(){
        return SINGLE;
    }

    public void test(){
        System.out.println("单例的测试类");
    }

}
