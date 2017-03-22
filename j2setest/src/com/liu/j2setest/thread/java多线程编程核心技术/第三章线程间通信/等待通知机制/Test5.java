package com.liu.j2setest.thread.java多线程编程核心技术.第三章线程间通信.等待通知机制;

import java.io.*;

/**
 * Created by liuzhilei on 2017/3/22.
 * 管道流进行线程间通信，字节流 PipedOutputStream PipedInputStream
 * 字符流于此类似 PipedWriter PipedReader
 */
public class Test5 {
    public static void main(String[] args) {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();
        try {

            outputStream.connect(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new WriteThread(writeData, outputStream);
        Thread thread1 = new ReadThread(readData, inputStream);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.start();
    }
}

class WriteData {

    public void writeMethod(PipedOutputStream out) {
        System.out.println("write: ");
        try {
            for (int i = 0; i < 300; i++) {
                String data = i + 1 + "";
                out.write(data.getBytes());
                System.out.print(data);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData {
    public void readMethod(PipedInputStream in) {
        System.out.println("read: ");
        byte[] bytes = new byte[20];
        try {
            int read = in.read(bytes);
            while (read != -1) {
                String str = new String(bytes, 0, read);
                System.out.print(str);
                read = in.read(bytes);
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteThread extends Thread {
    private WriteData writeData;
    private PipedOutputStream outputStream;

    public WriteThread(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        writeData.writeMethod(outputStream);
    }
}

class ReadThread extends Thread {
    private ReadData readData;
    private PipedInputStream inputStream;

    public ReadThread(ReadData readData, PipedInputStream inputStream) {
        this.readData = readData;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        readData.readMethod(inputStream);
    }
}
