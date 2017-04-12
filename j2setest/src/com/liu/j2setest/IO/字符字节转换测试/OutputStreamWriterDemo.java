package com.liu.j2setest.IO.字符字节转换测试;

import java.io.*;

/**
 * Created by liuzhilei on 2017/4/12.
 *
 * An OutputStreamWriter is a bridge from character streams to byte streams:
 * Characters written to it are encoded into bytes
 *
 * OutputStreamWriter 是字符到字节的桥梁，字符编码成字节
 */
public class OutputStreamWriterDemo {
    public static void main(String[] args) {
        writer();
    }

    public static void writer() {
        /*//字节流
        InputStream inputStream = System.in;
        //字节流 转 字符流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //缓冲区
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //输出流
        OutputStream outputStream = System.out;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        //缓冲区
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);*/

        //下面是简写格式
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if ("exit".equals(line)) break;
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
