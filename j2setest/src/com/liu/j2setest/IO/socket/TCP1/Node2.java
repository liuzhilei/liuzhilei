package com.liu.j2setest.IO.socket.TCP1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by liuzhilei on 2018/2/6.
 * 指定的组播地址和端口，要和node1相同，就可以加入与node1相同的组播组，然后通过multicastSocket的receive方法就可以不断的
 * 接收到从node1发来的消息.
 * 对于组播模式，没有客户端与服务端的概念，每个节点地方为都是等同的，都可以接收和发送消息；如果想增加节点，只需要加入到组中就可以
 */
public class Node2 {
    private static int port = 8000;
    private static String address = "228.0.0.4";

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(address);
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(group);
            byte[] bytes = new byte[1024];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                multicastSocket.receive(datagramPacket);
                String msg = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("receive from node1 :" + msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
