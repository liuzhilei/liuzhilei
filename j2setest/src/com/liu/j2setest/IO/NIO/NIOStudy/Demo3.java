package com.liu.j2setest.IO.NIO.NIOStudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by liuzhilei on 2017/12/27.
 * 文件锁 FileLock
 * 文件锁使用FileLock类来完成的，此对象需要依赖FileChannel进行实例化。
 * 文件锁的方式
 * 独占锁：只允许一个线程对文件进行读写操作
 * 共享锁：允许多个线程对文件进行读取
 * <p/>
 * 文件锁定以整个java虚拟机来保持，但不适用于控制同一虚拟机内多个线程对文件的访问。所以多个线程可以安全的使用文件锁定对象。
 * lock(): 获取对此通道的文件的独占锁定
 * lock(long position, long size, boolean shared)：获取对此通道的文件给定区域的锁定
 * tryLock() throws IOException：试图获取对此通道的文件的独占锁定
 * tryLock(long position, long size, boolean shared) throws IOException：试图获取对此通道的文件给定区域的锁定
 * <p/>
 * lock是阻塞方法，锁定范围可以随着文件的增大而增大。无参lock默认为独占锁，有参lock(0L, Long.MAX_VALUE, true)为共享锁。
 * tryLock是非阻塞方法，当未获得锁时返回null。无参tryLock默认为独占锁，有参tryLock(0L, Long.MAX_VALUE, true)为共享锁
 */
public class Demo3 {
    public static void main(String[] args) {
        File file = new File("D:" + File.separator + "tttt.txt");
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannel = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileChannel = fileOutputStream.getChannel();

            //试图锁定
            FileLock lock = fileChannel.tryLock();
            if (lock != null) {
                System.out.println(file.getName() + "文件锁定");
                Thread.sleep(5000);

                //解除锁定
                lock.release();
                System.out.println(file.getName() + "解除锁定");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
