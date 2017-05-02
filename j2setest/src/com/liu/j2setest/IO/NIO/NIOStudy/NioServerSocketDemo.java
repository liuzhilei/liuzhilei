package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by liuzhilei on 2017/5/2.
 * serverSocketChannel不完整示例
 */
public class NioServerSocketDemo {

    public void demo() {
        try {
            //打开serverSocketChannel
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), 10086));
            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);

            //while (true){
            // 监听新进来的连接，如果是阻塞模式会一直阻塞，直到有新进来的连接；如果channel是非阻塞模式，会立即返回，没有就返回null
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {

            }
            //}


            serverSocketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
