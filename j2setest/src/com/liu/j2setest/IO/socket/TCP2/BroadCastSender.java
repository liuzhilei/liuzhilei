package com.liu.j2setest.IO.socket.TCP2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by liuzhilei on 2018/2/7.
 * 发送端，所属网段为10.1.81.43，子网掩码为255.255.255.0，所以广播地址为192.168.0.255，
 * 然后往该网络的所有机器的8888端口发送hello消息，接收端将受到此消息
 */
public class BroadCastSender {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("10.1.81.255");
            DatagramSocket ds = new DatagramSocket();
            String msg = "hello";
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, ip, 8888);
            ds.send(dp);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
