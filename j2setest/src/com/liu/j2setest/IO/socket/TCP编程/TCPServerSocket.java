package com.liu.j2setest.IO.socket.TCP编程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liuzhilei on 2017/4/14.
 * 基于TCP协议的socket通信，实现用户登录，服务端
 * TCP协议是面向连接的，可靠的，有序的，以字节流的方式发送数据
 * <p/>
 * 对于同一个socket，如果关闭了输出流，则与该输出流关联的socket也会被关闭，所以一般不用关闭流，直接关闭socket即可
 */
public class TCPServerSocket {

    public static void main(String[] args) {
        try {
            //创建服务器socket，指定绑定端口，并监听端口
            ServerSocket serverSocket = new ServerSocket(10086);//1024-65535 的某个端口
            //调用accept方法开始监听，等待客户端的连接
            Socket socket = serverSocket.accept();
            //获取输入流，并读取客户端信息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("我是服务器，客户端对我说：" + line);
            }
            socket.shutdownInput();

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("欢迎你登录");
            printWriter.flush();

            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
