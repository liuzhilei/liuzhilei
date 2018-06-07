package com.liu.j2setest.designpattern.SingleTon;

import java.io.*;

/**
 * Created by liuzhilei on 2017/7/21.
 */
public class Main {

    public static void main(String[] args) {
        SingleTon5 singleTon5 = SingleTon5.getInstance();

        try {
            System.out.println(singleTon5);

            File file = new File("111.out");
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(singleTon5);


            System.out.println("单例模式定义了readResolve方法，就可以保证序列化前后得到的都是同一个对象，真正实现了单例");
            System.out.println("==从文件读出来的实体==");
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            SingleTon5 singleTest1 = (SingleTon5) inputStream.readObject();

            System.out.println(singleTest1);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
