package com.liu.j2setest.IO.读取文件;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by liuzhilei on 2017/3/24.
 * <p/>
 * 方法一：使用java.util.Scanner进行对大文件的读取：
 * 遍历文件的所有行，允许对每一行进行处理，而且不保持对他的引用。总之没有把他们放入内存，可以防止内存溢出
 */
public class ScannerTest {

    public static void main(String[] args) {
        new ScannerTest().read();

    }

    public void read() {
        FileInputStream inputStream = null;
        Scanner scanner = null;
        try {
            inputStream = new FileInputStream("D:/test.txt");
            scanner = new Scanner(inputStream, "utf-8");
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
