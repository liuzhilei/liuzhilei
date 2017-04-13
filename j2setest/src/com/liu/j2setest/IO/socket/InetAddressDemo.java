package com.liu.j2setest.IO.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by liuzhilei on 2017/4/13.
 * <p/>
 * InetAddress用于表示网络上的硬件资源，主要是ip地址
 */
public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            //获取本机InetAddress实例
            InetAddress localHost = InetAddress.getLocalHost();
            //获取计算机名
            String hostName = localHost.getHostName();
            //获取ip地址
            String hostAddress = localHost.getHostAddress();
            System.out.println("本地计算机名：" + hostName);
            System.out.println("本地IP地址：" + hostAddress);

            //获取其他主机的InetAddress实例
            InetAddress.getByName("其他主机名");
            InetAddress.getByName("IP地址");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
