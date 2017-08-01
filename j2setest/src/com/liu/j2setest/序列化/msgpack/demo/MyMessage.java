package com.liu.j2setest.序列化.msgpack.demo;

import com.liu.j2setest.序列化.Person;
import org.msgpack.annotation.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhilei on 2017/7/31.
 */
@Message
public class MyMessage {

    public String name;
    public double version;
    public List<String> list = new ArrayList<String>();
    public Map<String, String> map = new HashMap<String, String>();
    public List<Person> list1 = new ArrayList<Person>();
    public Map<String, Person> map1 = new HashMap<String, Person>();
    public Person person;


}
