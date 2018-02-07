package com.liu.j2setest.IO.socket.TCP2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by liuzhilei on 2018/2/7.
 * socket通信的广播模式。
 * 广播模式也属于一对多的通信模式。
 * 但是与组播模式不同的是，他向路由器连接的所有主机都发送消息而不管主机想不想要
 * 组播模式只会向加入组的成员发送消息
 * JDK提供了{@link java.net.DatagramSocket}类以实现广播通信功能
 */
public class BroadCastReceiver {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(8888);
            byte[] bytes = new byte[10];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            datagramSocket.receive(datagramPacket);
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
