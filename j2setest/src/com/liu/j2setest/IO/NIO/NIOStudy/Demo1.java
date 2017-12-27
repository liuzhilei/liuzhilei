package com.liu.j2setest.IO.NIO.NIOStudy;

import java.nio.IntBuffer;

/**
 * Created by liuzhilei on 2017/12/27.
 * 分析缓冲区中 position,limit,capacity 操作前后的变化情况
 */
public class Demo1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);

        //未操作缓冲区前，输出属性值
        System.out.println("position = " + buffer.position() + ",limit = " + buffer.limit() + ",capacity = " + buffer.capacity());

        //放入值
        buffer.put(1);
        buffer.put(1);

        //放入数据后，输出属性值
        System.out.println("position = " + buffer.position() + ",limit = " + buffer.limit() + ",capacity = " + buffer.capacity());

        //写模式切换成读模式
        buffer.flip();

        //切换模式后，输出属性值
        System.out.println("position = " + buffer.position() + ",limit = " + buffer.limit() + ",capacity = " + buffer.capacity());
    }
}
