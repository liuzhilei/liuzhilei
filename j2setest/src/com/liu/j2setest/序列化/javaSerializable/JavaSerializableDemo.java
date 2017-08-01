package com.liu.j2setest.序列化.javaSerializable;

import com.liu.j2setest.序列化.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by liuzhilei on 2017/8/1.
 * java自带的序列化方式，实体类需要实现Serializable接口
 */
public class JavaSerializableDemo {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("金晶晶");
        person.setAge(28);

        System.out.println("======序列化======");
        //定义一个字节数组输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //定义一个对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(os);
        //将对象写入输出流中，进行序列化
        oos.writeObject(person);
        byte[] bytes = os.toByteArray();
        System.out.println(bytes.length);

        System.out.println("======反序列化======");
        //定义一个字节数组输入流
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        //对象输入流
        ObjectInputStream ois = new ObjectInputStream(is);
        //反序列化
        Person person1 = (Person) ois.readObject();

        System.out.println(person1.getName());
        System.out.println(person1.getAge());

    }
}
