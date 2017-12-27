package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuzhilei on 2017/12/27.
 * 理解通道：channel
 */
public class Demo2 {

    public static void main(String[] args) {
        String[] info = {"1", "2", "3", "4", "5"};
        File file = new File("D:" + File.separator + "tttt.txt");
        FileOutputStream fileOutputStream = null;
        FileChannel channel = null;
        try {
            fileOutputStream = new FileOutputStream(file);

            //得到输出的通道
            channel = fileOutputStream.getChannel();

            //分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);

            for (int i = 0; i < info.length; i++) {
                //写到缓冲区中
                buffer.put(info[i].getBytes());
            }

            //切换成读模式
            buffer.flip();

            //输出缓冲区内容
            channel.write(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
