package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuzhilei on 2017/5/3.
 * 利用NIO实现大文件读写
 */
public class ReadAndWrite {
    public static void main(String[] args) {
        byte[] bytes = new byte[10 * 1024 * 1024];

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 10);
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel channel = null;
        FileChannel outChannel = null;
        try {
            inputStream = new FileInputStream("D://api.rdgame.zip");
            outputStream = new FileOutputStream("D://backup.zip");

            channel = inputStream.getChannel();
            outChannel = outputStream.getChannel();

            long start = System.currentTimeMillis();
            /**
             *  用下面两个方法读取文件时间消耗明显很高
             *  inputStream.read(bytes);
                channel.read(buffer);

             */
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
            System.out.println(channel.size() / 1024);

            long end = System.currentTimeMillis();
            System.out.println("读取文件的时间花费了" + (end - start) + "秒");

            start = System.currentTimeMillis();

            mappedByteBuffer.flip();
            outChannel.write(mappedByteBuffer);
            end = System.currentTimeMillis();
            System.out.println("写入文件的时间花费了" + (end - start) + "秒");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
