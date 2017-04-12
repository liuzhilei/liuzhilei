package com.liu.j2setest.IO.字符字节转换测试;

import org.omg.CORBA_2_3.portable.*;

import java.io.*;
import java.io.InputStream;

/**
 * Created by liuzhilei on 2017/4/12.
 *
 * An InputStreamReader is a bridge from byte streams to character streams: It
 * reads bytes and decodes them into characters
 * InputStreamReader是字节到字符的桥梁，读取字节并且解码成字符
 */
public class InputStreamReaderDemo {

    public static void main(String[] args) {
        reader();
    }

    public static void reader() {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            InputStream is = new FileInputStream("D://test.txt");
            inputStreamReader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.println(readLine);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
