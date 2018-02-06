package com.liu.j2setest.IO.socket.TCP1;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Created by liuzhilei on 2018/2/5.
 * TCP包下是socket的单播模式，这个例子是组播模式,利用的是UDP
 * 特点：
 * 1.节省网络资源
 * 2.有针对性的向组内成员传播
 * 3.可以在互联网上进行传播
 * 4.没有可靠地传输协议，会导致数据不可靠
 * 组播通信相当于把主机与主机之间的通信压力转嫁到了路由器上面。ip层面指定的组播地址称为D类地址，范围是224.0.0.0~239.255.255.255，
 * 其中224.0.0.0~244.0.0.255用于局域网，224.0.1.0~238.255.255.255用于因特网。
 * tomcat默认的组播地址为228.0.0.4，tomcat涉及到组播通信是因为集群，集群会涉及到内存共享，所以需要是用组播通信进行数据同步。
 * JDK提供{@link java.net.MulticastSocket}实现了组播通信
 */
public class Node1 {
    private static int port = 8000;
    private static String address = "228.0.0.4";

    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName(address);
            MulticastSocket multicastSocket = new MulticastSocket(port);
            multicastSocket.joinGroup(group);
            while (true) {
                String msg = "hello from node1";
                byte[] bytes = msg.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, group, port);
                multicastSocket.send(datagramPacket);
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
