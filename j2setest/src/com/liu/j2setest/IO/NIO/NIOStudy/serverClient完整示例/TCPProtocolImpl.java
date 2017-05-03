package com.liu.j2setest.IO.NIO.NIOStudy.serverClient完整示例;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuzhilei on 2017/5/3.
 */
public class TCPProtocolImpl implements TCPProtocol {
    private int bufferSize;

    public TCPProtocolImpl(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * 将 可接收 调整为 可读取
     *
     * @param key
     * @throws IOException
     */
    @Override
    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    @Override
    public void handleRead(SelectionKey key) throws IOException {
        //获取与客户端通信的通道
        SocketChannel socketChannel = (SocketChannel) key.channel();

        //得到并清空缓冲区
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();

        //通道中信息读到buffer中，返回读取到的字节数
        int read = socketChannel.read(buffer);

        if (read == -1) {
            //没有读取到内容
            socketChannel.close();
        } else {
            //反转，写模式改为读模式
            buffer.flip();

            //将buffer中的字节转化为utf-8字符串
            String receiveString = Charset.forName("utf-8").newDecoder().decode(buffer).toString();

            System.out.println("接收到来自" + socketChannel.socket().getRemoteSocketAddress() + "的信息：" + receiveString);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String f = format.format(new Date());
            //准备发送的文本
            String sendString = "你好，客户端. @" + f + "，已经收到你的信息：" + receiveString;
            buffer = ByteBuffer.wrap(sendString.getBytes("utf-8"));
            socketChannel.write(buffer);

            //设置为下一次读取或者是写入做准备
            key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        }

    }

    @Override
    public void handleWrite(SelectionKey key) throws IOException {

    }
}
