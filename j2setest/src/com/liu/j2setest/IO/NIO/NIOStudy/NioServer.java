package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;

/**
 * Created by liuzhilei on 2017/4/18.
 */
public class NioServer {

    private int flag = 0;
    //缓冲区大小
    private int block = 4096;
    //发送数据缓冲区
    private ByteBuffer sendBuffer = ByteBuffer.allocate(block);
    //接收数据缓冲区
    private ByteBuffer receiveBuffer = ByteBuffer.allocate(block);
    private Selector selector;

    public NioServer(int port) throws IOException {

    }


}
