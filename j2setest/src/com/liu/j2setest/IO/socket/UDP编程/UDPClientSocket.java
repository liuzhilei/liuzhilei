package com.liu.j2setest.IO.socket.UDP编程;

import java.io.IOException;
import java.net.*;

/**
 * Created by liuzhilei on 2017/4/14.
 * 客户端
 */
public class UDPClientSocket {
    public static void main(String[] args) {

        try {
            //定义服务器的地址，端口号，以及要向服务器发送的数据
            InetAddress localhost = InetAddress.getByName("localhost");
            int port = 10010;
            byte[] bytes = "用户名：刘志磊；密码：123456".getBytes();
            //定义发送数据报
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, localhost, port);
            //创建DatagramSocket对象
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);

            //接收服务器的响应

            //创建接收数据报
            byte[] bytes1 = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes1, bytes1.length);
            //接收消息，消息接收前一直阻塞
            socket.receive(datagramPacket);
            System.out.println("服务器发来的响应是：" + new String(bytes1, 0, bytes1.length));

            socket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
