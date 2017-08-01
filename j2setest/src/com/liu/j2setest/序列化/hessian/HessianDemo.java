package com.liu.j2setest.序列化.hessian;

import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.liu.j2setest.序列化.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by liuzhilei on 2017/8/1.
 * hessian序列化方式
 */
public class HessianDemo {
    public static void main(String[] args) throws Exception{
        Person person = new Person();
        person.setName("金晶晶");
        person.setAge(28);

        System.out.println("======hessian序列化======");
        //定义字节数组输出流
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //hessian序列化输出流，执行序列化
        HessianOutput hop = new HessianOutput(os);
        hop.writeObject(person);
        byte[] bytes = os.toByteArray();

        System.out.println(bytes.length);

        System.out.println("======hessian反序列化======");
        //定义字节数组输入流
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput hip = new HessianInput(is);
        Person person1 = (Person) hip.readObject();

        System.out.println(person1.getName());
        System.out.println(person1.getAge());
    }
}
