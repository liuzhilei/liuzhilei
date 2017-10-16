package com.liu.j2setest.serializable.msgpack.demo;

import com.liu.j2setest.serializable.Person;
import org.msgpack.MessagePack;

/**
 * Created by liuzhilei on 2017/7/31.
 * 利用msgPack进行序列化和反序列化
 * msgPack对于对象的嵌套，list，map都很适用
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MyMessage myMessage = new MyMessage();
        myMessage.person = new Person();
        myMessage.person.setName("刘志磊");
        myMessage.person.setAge(26);

        myMessage.list.add("123");
        myMessage.list.add("456");

        myMessage.list1.add(myMessage.person);

        myMessage.name = "myMessage";
        myMessage.version = 0.6;

        myMessage.map.put("abc", "def");
        myMessage.map1.put("实体", myMessage.person);

        MessagePack messagePack = new MessagePack();

        //序列化
        byte[] write = messagePack.write(myMessage);
        System.out.println("序列化后的长度" + write.length);

        //反序列化
        MyMessage read = messagePack.read(write, MyMessage.class);
        System.out.println("反序列化后：");
        System.out.println(read.list);
        System.out.println(read.list1);
        System.out.println(read.map);
        System.out.println(read.map1);
        System.out.println(read.name);
        System.out.println(read.version);
        System.out.println(read.person);

    }
}
