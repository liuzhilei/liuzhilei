package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by liuzhilei on 2017/4/18.
 * 使用NIO写数据
 */
public class Write {
    public static void main(String[] args) {
        byte[] data = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};
        try {
            FileOutputStream stream = new FileOutputStream("D://write.txt");
            //打开通道
            FileChannel channel = stream.getChannel();
            //新建一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(20);
            //先将数据写入buffer
            for (byte b : data) {
                buffer.put(b);
            }
            //反转buffer，写模式切换为读模式
            buffer.flip();
            //将数据从buffer送入channel通道中
            channel.write(buffer);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
