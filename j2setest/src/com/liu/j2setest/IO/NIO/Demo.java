package com.liu.j2setest.IO.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liuzhilei on 2017/4/17.
 */
public class Demo {

    public void test() {
        try {
            //1.打开一个socketChannel链接
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            //2.绑定监听端口，并且设置为非阻塞模式
            socketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), 10086));
            socketChannel.configureBlocking(false);//FileChannel不能设置为非阻塞模式
            //创建Reactor线程，创建多路复用器selector，并且启动线程
            Selector selector = Selector.open();
            new Thread(new ReactorTask()).start();
            //将serverSocketChannel注册到Reactor线程的多路复用器Selector上，监听ACCEPT事件
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT, null);
            //多路复用器selector 在线程run方法的无限循环体内轮询准备就绪的key,该方法会一直阻塞，直到至少有一个注册的通道准备就绪了
            int num = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //处理IO操作
                //下面是判断每个键的就绪事件
                if (key.isAcceptable()) {

                } else if (key.isConnectable()) {

                } else if (key.isReadable()) {

                } else if (key.isWritable()) {

                }
                iterator.remove();

            }

            selectionKey.interestOps();
            selectionKey.readyOps();
            SelectableChannel channel = selectionKey.channel();
            Selector selector1 = selectionKey.selector();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ReactorTask extends Thread {

}
