package com.liu.j2setest.IO.socket.UDP编程;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by liuzhilei on 2017/4/14.
 * UDP 无连接，不可靠，无序，速度快
 * <p/>
 * 服务端，实现基于udp的用户登录
 */
public class UDPServerSocket {
    public static void main(String[] args) {
        try {
            //创建服务器端DatagramSocket，指定端口
            DatagramSocket socket = new DatagramSocket(10010);
            //创建接收数据报，用于接收客户端发送的数据
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
            //接收客户端发送的数据，此方法在接收到消息之前一直阻塞
            socket.receive(packet);
            //读取数据
            System.out.println("我是服务器，客户端告诉我：" + new String(bytes, 0, bytes.length));


            //向客户端响应数据
            //获取客户端地址，端口号，数据
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            byte[] bytes1 = "欢迎您！".getBytes();
            //创建发送数据报，包含响应的数据信息
            DatagramPacket packet1 = new DatagramPacket(bytes1, bytes1.length, address, port);
            socket.send(packet1);

            socket.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
