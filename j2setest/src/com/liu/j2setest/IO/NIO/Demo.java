package com.liu.j2setest.IO.NIO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liuzhilei on 2017/4/17.
 */
public class Demo {

    /**
     * demo示例主要看这个
     * 2017.05.02
     */
    public void mainDemo() {
        try {
            //打开一个socketChannel
            ServerSocketChannel channel = ServerSocketChannel.open();
            //创建一个selector
            Selector selector = Selector.open();
            //设置为非阻塞模式，selector和channel配合使用，channel必须设置为非阻塞模式
            channel.configureBlocking(false);
            /**
             * 将channel注册到selector上，interest事件为接收就绪和读就绪
             *
             * interest事件分类：
             * SelectionKey.OP_CONNECT  连接就绪
             * SelectionKey.OP_ACCEPT   接收就绪
             * SelectionKey.OP_READ     读就绪
             * SelectionKey.OP_WRITE    写就绪
             */
            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_READ);

            //从selectionKey访问Channel和Selector
            Channel channel1 = selectionKey.channel();
            Selector selector1 = selectionKey.selector();
            while (true) {
                //返回的值表示有多少通道已经就绪
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                //返回已选择的键集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //遍历已选择的键集合来访问就绪的通道，并检测各个键所对应通道的就绪时间
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isConnectable()) {
                        System.out.println("连接就绪");
                    } else if (key.isAcceptable()) {
                        System.out.println("接收就绪");
                    } else if (key.isReadable()) {
                        System.out.println("读就绪");
                    } else if (key.isWritable()) {
                        System.out.println("写就绪");
                    }
                    //注意，selector不会从已经选择的键集合中移除SelectionKey实例，需要自己手动移除
                    iterator.remove();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
