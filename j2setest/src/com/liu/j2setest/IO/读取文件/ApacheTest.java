package com.liu.j2setest.IO.读取文件;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

/**
 * Created by liuzhilei on 2017/3/24.
 * 方法二：使用apache的commons IO库实现
 */
public class ApacheTest {
    public static void main(String[] args) {
        File file = new File("D:/test.txt");
        LineIterator iterator = null;
        try {
            iterator = FileUtils.lineIterator(file, "utf-8");
            while (iterator.hasNext()) {
                String string = iterator.nextLine();
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (iterator != null) {
                iterator.close();
            }
        }

    }
}
