package com.liu.j2setest.IO.NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuzhilei on 2017/4/17.
 * Channel和buffer例子
 */
public class ChannelAndBufferDemo {
    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile("d://test.txt", "rw");
            FileChannel channel = file.getChannel();
            //分配一个容量为48的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(48);
            //读取数据到缓冲区
            int read = channel.read(buffer);
            //channel.write(buffer)  这个是把buffer缓冲区的数据写到通道
            while (read != -1) {
                System.out.println("Read : " + read);
                /**
                 * 反转buffer，该方法通常结合与buffer从一个地方传输到另
                 * 一个地方共同使用的方法，就是将buffer将写模式切换成读模式
                 */
                buffer.flip();

                while (buffer.hasRemaining()) {
                    //每次读取一个字节
                    System.out.print((char) buffer.get());
                }

                //buffer.clear(); //会清空整个缓冲区
                buffer.compact();//清空读取过的数据，没有读到的不会被清空
                read = channel.read(buffer);

            }
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
