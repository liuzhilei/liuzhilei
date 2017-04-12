package com.liu.j2setest.内部类和内部接口.innerInterface;

/**
 * Created by liuzhilei on 2017/4/12.
 * 内部接口,可以用于回调
 */
public class outerInterface {

    private InnerInterface innerInterface;

    public outerInterface(InnerInterface innerInterface) {
        this.innerInterface = innerInterface;
    }

    public interface InnerInterface {
        String inner(String string);
    }

    public String outerMethod(String string) {
        System.out.println("外部方法");
        String inner = innerInterface.inner(string);
        return inner;
    }

}

class Main {
    public static void main(String[] args) {
        outerInterface outerInterface = new outerInterface(new outerInterface.InnerInterface() {
            @Override
            public String inner(String i) {
                //这里可以用于内部接口的具体实现
                return i.toUpperCase();
            }
        });

        String str = "hello world";
        System.out.println("修改之前：" + str);
        System.out.println("修改之后：" + outerInterface.outerMethod(str));
    }
}
