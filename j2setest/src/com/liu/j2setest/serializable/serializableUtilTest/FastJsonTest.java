package com.liu.j2setest.serializable.serializableUtilTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by liuzhilei on 2017/10/14.
 */
public class FastJsonTest implements Serializable{

    private transient String num;

    @JSONField(name = "n")
    private String name;

    @JSONField(name = "a")
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public static void main(String[] args) {

        FastJsonTest fastJsonTest = new FastJsonTest();
        fastJsonTest.setName("name");
        fastJsonTest.setAge("age");
        fastJsonTest.setNum("122");

        System.out.println(JSON.toJSONString(fastJsonTest));

    }

}
