package com.liu.j2setest.IO.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by liuzhilei on 2017/5/2.
 */
public class NioSocketDemo {

    public void demo() {
        try {
            //打开socketChannel
            SocketChannel channel = SocketChannel.open();
            channel.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 10086));

            //从socketChannel中读数据
            ByteBuffer buffer = ByteBuffer.allocate(48);
            int read = channel.read(buffer);

            //写入socketChannel
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            while (buf.hasRemaining()) {
                channel.write(buf);
            }


            //关闭socketChannel
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
