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

    public static void main(String[] args) {
        allocateMemory();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void allocateMemory() {
        List<byte[]> list = new ArrayList<byte[]>();
        int size = 1024 * 1024 * 400;
        int len = size / (20 * 1024);
        for (int i = 0; i < len; i++) {
            byte[] bytes = new byte[20 * 1024];
            list.add(bytes);
        }
    }
}
