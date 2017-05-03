package com.liu.j2setest.IO.NIO.NIOStudy.serverClient完整示例;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by liuzhilei on 2017/4/18.
 */
public class NioServer {

    //缓冲区大小
    private static final int BufferSize = 1024;

    //超时时间，单位毫秒
    private static final int TimeOut = 3000;
    //本地监听端口
    private static final int ListenPort = 10086;

    public static void main(String[] args) {
        try {
            //创建选择器
            Selector selector = Selector.open();
            //打开监听通道
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            //与本地端口绑定
            socketChannel.socket().bind(new InetSocketAddress(ListenPort));
            //设置为非阻塞
            socketChannel.configureBlocking(false);
            //channel注册到selector选择器上，接收就绪
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //创建一个处理协议的实现类，由他具体操作
            TCPProtocol protocol = new TCPProtocolImpl(BufferSize);

            while (true) {
                //等待某通道就绪
                int keys = selector.select(TimeOut);
                if (keys == 0) {
                    System.out.println("继续等待...");
                    continue;
                }
                //返回已经就绪的所有键集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    try {
                        if (key.isAcceptable()) {
                            System.out.println("acceptable");
                            /**
                             * 这里将interest由OP_ACCEPT 改为OP_READ
                             * 如果不执行下面的语句，会一直是accept状态（因为初始值设置的是accept），无法进入
                             * 后面的两个if语句
                             * 就会一直打印上面的语句
                             */
                            protocol.handleAccept(key);
                        }
                        if (key.isReadable()) {
                            //从客户端读取数据
                            System.out.println("readable");
                            protocol.handleRead(key);
                        }
                        if (key.isValid() && key.isWritable()) {
                            protocol.handleWrite(key);
                        }
                    } catch (IOException e) {
                        iterator.remove();
                        continue;
                    }
                    //移除处理过的键
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
