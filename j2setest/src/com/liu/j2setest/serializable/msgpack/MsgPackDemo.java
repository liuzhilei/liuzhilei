package com.liu.j2setest.serializable.msgpack;

import com.liu.j2setest.serializable.Person;
import org.msgpack.MessagePack;

/**
 * Created by liuzhilei on 2017/8/1.
 * msgPack序列化方式，该类用于比较和hessian和java自带序列化后的字节长度
 * msgPack序列化的时候，不会写入字段名称，会按照顺序写入值，可以想象成一个数组
 */
public class MsgPackDemo {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("金晶晶");
        person.setAge(28);

        System.out.println("======msgPack序列化======");
        MessagePack messagePack = new MessagePack();
        byte[] bytes = messagePack.write(person);
        System.out.println(bytes.length);

        System.out.println("======msgPack反序列化======");
        Person person1 = messagePack.read(bytes, Person.class);
        System.out.println(person1.getName());
        System.out.println(person1.getAge());
    }
}
