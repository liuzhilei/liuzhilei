package com.liu.j2setest.IO.NIO.NIOStudy.serverClient完整示例;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by liuzhilei on 2017/4/18.
 */
public class NioClient {
    //选择器
    private Selector selector;
    //与服务器通信的通道
    SocketChannel socketChannel;
    //要链接的服务器ip
    private String hostIp;
    //要链接的远程服务器在监听的端口
    private int hostListeningPort;

    /**
     * 构造函数
     *
     * @param HostIp
     * @param hostListeningPort
     * @throws IOException
     */
    public NioClient(String HostIp, int hostListeningPort) throws IOException {
        this.hostIp = HostIp;
        this.hostListeningPort = hostListeningPort;

        initialize();
    }


    private void initialize() throws IOException {
        //打开监听信道并设置为非阻塞
        socketChannel = SocketChannel.open(new InetSocketAddress(hostIp, hostListeningPort));
        socketChannel.configureBlocking(false);

        //打开选择器，并将通道注册到选择器
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        // 启动读取线程
        new NIOClientReadThread(selector);
    }

    /**
     * 发送字符串到服务器
     *
     * @param message
     * @throws IOException
     */
    public void sendMsg(String message) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));

        int r = socketChannel.write(writeBuffer);
        System.out.println("write return:" + r);
        //socketChannel.
    }

    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient(InetAddress.getByName("localhost").getHostAddress(), 10086);
        //for (int i = 0; i < 10; i++)
        int i = 0;
        while (true) {
            client.sendMsg("Nio" + (i++));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
