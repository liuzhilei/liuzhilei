package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuzhilei on 2017/4/18.
 * 使用nio读取数据
 */
public class Read {
    public static void main(String[] args) {
        try {
            //读取文件
            FileInputStream stream = new FileInputStream("D://test.txt");
            //打开通道
            FileChannel channel = stream.getChannel();
            //创建一个ByteBuffer缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            //将数据从通道读到缓冲区buffer中
            while (channel.read(buffer) != -1){
                //反转buffer，写模式切换为读模式
                buffer.flip();
                //遍历循环buffer
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                //清空buffer
                buffer.clear();
            }

            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
