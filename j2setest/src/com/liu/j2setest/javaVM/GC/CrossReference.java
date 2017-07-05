package com.liu.j2setest.javaVM.GC;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhilei on 2017/7/5.
 *
 * 数组动态扩容导致频繁FGC ，但是又不能回收空间
 *
 * -Xmx500M -Xms500M -Xmn200M -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=90 -XX:+CMSScavengeBeforeRemark
 *
 * -Xmx500M     堆内存最大可用内存
 * -Xms500M     堆内存最小可用内存，一般和最大内存设置一样
 * -Xmn200M     新生代可用内存
 * -XX:+UseConcMarkSweepGC      使用cms进行gc
 * -XX:+UseCMSInitiatingOccupancyOnly   一般测试用，JVM不基于运行时收集的数据来启动CMS垃圾收集周期
 * -XX:CMSInitiatingOccupancyFraction=90    表示老年代空间占用90%时会触发CMS进行垃圾回收
 * -XX:+CMSScavengeBeforeRemark     执行CMS remark之前进行一次youngGC，这样能有效降低remark的时间
 *
 * 这段代码导致执行垃圾回收不能正常回收空间 ，详见word文档
 */
public class CrossReference {

    private static int unit = 20 * 1024;

    public static void main(String[] args) throws Exception{
        Thread.sleep(5000);
        System.out.println("allocate start************");
        allocate();
        Thread.sleep(1000);
        System.out.println("allocate end************");
        System.in.read();
    }

    private static void allocate() throws Exception{
        int size = 1024 * 1024 * 400; // 400M
        int len = size / unit;
        List<BigObject> list = new ArrayList<BigObject>();

        for(int i = 1; i <= len; i++){
            BigObject bigObject = new BigObject();
            list.add(bigObject);
            Thread.sleep(1);
            System.out.println(i);
        }
    }

    private static class BigObject{
        private byte[] foo;
        BigObject(){
            foo = new byte[unit];
        }
    }
}
