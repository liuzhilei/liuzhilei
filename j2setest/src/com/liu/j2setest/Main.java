package com.liu.j2setest;

import com.alibaba.fastjson.JSON;
import com.liu.j2setest.compare.Person;
import com.liu.j2setest.reflect.demo4.User;
import com.liu.j2setest.serializable.serializableUtilTest.FastJsonTest;
import com.liu.j2setest.thread.maintest.多线程异常捕获.线程池情况.ThreadPool;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhilei on 2017/1/10.
 */
public class Main {

    private static final Object myLock = new Object();

    private static final ReentrantLock mainLock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        //compareTable();
        //recordTable();
        //repairTable();
        outSql();

    }

    public int add(int i) {
        i++;
        return i;
    }

    public static void compareTable() {
        String str = "CREATE TABLE `sdkChuguanStock_compare_task_%s` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',\n" +
                "  `sku` bigint(20) NOT NULL COMMENT 'sku',\n" +
                "  `chuguanNum` mediumtext NOT NULL COMMENT '出管现货在途，以及内配系统的数据',\n" +
                "  `stockNum` mediumtext NOT NULL COMMENT 'sdk数据',\n" +
                "  `chuguan_sub_stock` mediumtext NOT NULL COMMENT 'chuguan和sdk差异值',\n" +
                "  `stable_count` int(11) NOT NULL COMMENT '稳定次数',\n" +
                "  `compared_count` int(11) NOT NULL COMMENT '对比次数',\n" +
                "  `status` smallint(6) NOT NULL COMMENT '任务状态',\n" +
                "  `status_desc` varchar(20) NOT NULL COMMENT '任务状态描述',\n" +
                "  `createtime` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modifytime` datetime NOT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `idx_compare_sku` (`sku`),\n" +
                "  KEY `idx_compare_status` (`status`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自营和出管现货、在途、内配出入对比任务表';";

        for (int i = 0; i < 128; i++) {
            String format = String.format(str, i);
            System.out.println(format);
            System.out.println();
        }
    }

    public static void repairTable() {
        String str = "CREATE TABLE `sdkChuguanStock_repair_task_%s` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',\n" +
                "  `sku` bigint(20) NOT NULL COMMENT 'sku',\n" +
                "  `chuguan_sub_stock` mediumtext NOT NULL COMMENT '出管现货在途，以及内配系统的数据',\n" +
                "  `status` smallint(6) NOT NULL COMMENT '任务状态',\n" +
                "  `status_desc` varchar(20) NOT NULL COMMENT '任务状态描述',\n" +
                "  `remark` varchar(50) DEFAULT NULL COMMENT '备注',\n" +
                "  `createtime` datetime NOT NULL COMMENT '创建时间',\n" +
                "  `modifytime` datetime NOT NULL COMMENT '修改时间',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `idx_repair_sku` (`sku`),\n" +
                "  KEY `idx_repair_status` (`status`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自营和出管现货、在途、内配出入对比修复表';";

        for (int i = 0; i < 128; i++) {
            String format = String.format(str, i);
            System.out.println(format);
            System.out.println();
        }
    }

    public static void outSql() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 128; i++) {
            sb.append("select * from stock_task_" + i + " where retryCount >= 9 and status = 0 and busiType in (401,402,403,404,405,408,409,40101,40201,40301,40302,40401,405,40801) union all");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }


    public static void mainTest() {
        System.out.println("static静态导包");
    }


}
