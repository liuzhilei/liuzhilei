package com.liu.j2setest.IO.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liuzhilei on 2017/4/13.
 * URL:统一资源定位，表示Internet上某一资源地址，协议名：资源名称
 * <p/>
 * 通过url的openStream()可以得到指定的资源输入流，通过流能够读取网页上的资源
 */
public class URLDemo {
    public static void main(String[] args) {
        try {
            URL baidu = new URL("http://www.baidu.com");
            System.out.println("协议名：" + baidu.getProtocol());
            System.out.println("域名：" + baidu.getHost());
            System.out.println("端口：" + baidu.getPort());

            //使用url读取网页内容
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(baidu.openStream()));
                String line = null;
                char[] chars = new char[100];
                while(bufferedReader.read(chars, 0, chars.length) > 0){
                    System.out.println(chars);
                }

                /* 读取的是字节，需要用上面的字符，通过InputStreamReader解码成字符，才能正常显示
                BufferedInputStream bufferedInputStream = new BufferedInputStream(baidu.openStream());
                byte[] bytes = new byte[100];
                while (bufferedInputStream.read(bytes,0,bytes.length) > 0){
                    System.out.println(bytes);
                }*/
                /*while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
