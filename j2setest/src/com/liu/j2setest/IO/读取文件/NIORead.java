package com.liu.j2setest.IO.读取文件;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by liuzhilei on 2017/5/3.
 * <p/>
 * 普通IO读取文件
 * 普通NIO读取文件
 * 利用NIO的mappedByteBuffer读取文件 三者的性能比较
 */
public class NIORead {
    //99M 大小
    private static String file = "D://Netty.pdf";

    public static void main(String[] args) {
        try {
            /**
             * 普通NIO读取，每次读取1024个字节
             * 不打印在控制台情况下，耗时 608 毫秒
             */
            //readByChannelTest(1024);

            /**
             * 使用内存文件映射来读取，从fileChannel拿到MappedByteBuffer，读取文件内容
             * 不打印在控制台情况下，耗时 45 毫秒
             */
            readByChannelTest3(1024);

            /**
             * 普通IO读取文件
             * 不打印在控制台情况下，耗时 139 毫秒
             */
            //readByBufferedStream();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 使用fileChannel读取文件，并打印在控制台
     *
     * @param allocate 每次读取的字节数
     * @throws IOException
     */
    public static void readByChannelTest(int allocate) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileChannel = fileInputStream.getChannel();
        long size = fileChannel.size();

        byte[] bytes = new byte[1024];
        ByteBuffer buffer = ByteBuffer.allocate(allocate);

        int len;
        while ((len = fileChannel.read(buffer)) != -1) {
            buffer.flip();

            //类似与inputStream的read(byte[],offset,len)方法读取
            buffer.get(bytes, 0, len);
            //System.out.println(new String(bytes, 0, len));

            //每次循环最后必须调用clear方法，将buffer中位置归零
            buffer.clear();
        }

        fileChannel.close();
        fileInputStream.close();

        long end = System.currentTimeMillis();
        System.out.println(String.format("文件大小：%s 字节", size));
        System.out.println(String.format("读取文件耗时为：%s 毫秒", end - start));

    }


    /**
     * 通过FileChannel.map()拿到MappedByteBuffer
     * 使用内存文件映射，速度会快很多
     *
     * @param allocate
     * @throws IOException
     */
    public static void readByChannelTest3(int allocate) throws IOException {
        long start = System.currentTimeMillis();

        //RandomAccessFile fis = new RandomAccessFile(new File(file), "rw");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileChannel = fis.getChannel();
        long size = fileChannel.size();

        //构建一个只读的MappedByteBuffer
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);

        /*
        //如果文件不大，可以一次性读取
        byte[] all = new byte[(int) size];
        mappedByteBuffer.get(all, 0, (int) size);
        System.out.println(new String(all, 0, all.length));
        */

        //如果文件很大，可以循环读取，计算应该循环多少次
        byte[] bytes = new byte[allocate];
        long cycle = size / allocate;
        //看能否整数倍读完
        int mode = (int) (size % allocate);

        for (int i = 0; i < cycle; i++) {
            //每次读取allocate个字节
            mappedByteBuffer.get(bytes);

            //打印会耗时
            //System.out.println(new String(bytes, 0, bytes.length));
        }
        if (mode > 0) {
            bytes = new byte[mode];
            mappedByteBuffer.get(bytes);

            //打印会耗时
            //System.out.println(new String(bytes, 0, bytes.length));
        }

        //关闭通道和文件流
        fileChannel.close();
        fis.close();

        long end = System.currentTimeMillis();
        System.out.println(String.format("文件大小：%s 字节", size));
        System.out.println(String.format("读取文件耗时为：%s 毫秒", end - start));

    }

    /**
     * 普通IO读取
     *
     * @throws IOException
     */
    public static void readByBufferedStream() throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        long size = inputStream.available();

        int len = 0;
        byte[] bytes = new byte[1024];

        while ((len = inputStream.read(bytes)) != -1) {
            //System.out.println(new String(bytes, 0, len));
        }

        inputStream.close();

        long end = System.currentTimeMillis();
        System.out.println(String.format("文件大小：%s 字节", size));
        System.out.println(String.format("读取文件耗时为：%s 毫秒", end - start));
    }


}
