package com.liu.j2setest.IO.socket.TCP;

import java.io.*;
import java.net.Socket;

/**
 * Created by liuzhilei on 2017/4/14.
 * 客户端
 * 这个例子属于单播模式：一对一的模式，发送，接收只在两者之间进行。
 * 缺点：有多少个主机就要进行多少次通信，效率极低
 */
public class TCPClientSocket {

    public static void main(String[] args) {
        try {
            //创建客户端socket，指定host和端口号
            Socket socket = new Socket("localhost", 10086);
            //获取输出流，用于向服务器发送
            OutputStream outputStream = socket.getOutputStream();
            //将输出流包装成打印流
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("用户名：liuzhilei，密码：123456");
            printWriter.flush();
            socket.shutdownOutput();


            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("我是客户端，接收到服务端的消息：" + line);
            }

            outputStream.close();
            bufferedReader.close();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
