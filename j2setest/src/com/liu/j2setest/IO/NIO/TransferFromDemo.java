package com.liu.j2setest.IO.NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Created by liuzhilei on 2017/4/17.
 * FileChannel的transferFrom方法可以从源通道传输到FileChannel
 */
public class TransferFromDemo {

    public static void main(String[] args) {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("D://fromfile.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();

            RandomAccessFile toFile = new RandomAccessFile("D://tofile.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            toChannel.transferFrom(fromChannel, 0, fromChannel.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
