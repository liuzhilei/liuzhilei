package com.liu.j2setest.zookeeperStudy;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by liuzhilei on 2017/5/16.
 */
public class ZookeeperDemo {
    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper = new ZooKeeper("192.168.147.89:2181", 2000, new Watcher() {
                //监控所有被触发的事件
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("已经触发" + event.getType() + "事件！");
                }
            });
            //创建一个目录节点
            zooKeeper.create("/liuzk", "liudata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            //创建一个子目录节点
            zooKeeper.create("/liuzk/childliuzkone", "childliuzkone".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            //取出目录节点数据
            System.out.println(new String(zooKeeper.getData("/liuzk", false, null)));
            //取出子目录节点列表
            System.out.println(zooKeeper.getChildren("/liuzk", true));
            //修改子目录节点数据
            zooKeeper.setData("/liuzk/childliuzkone", "modifychildliuzkone".getBytes(), -1);

            System.out.println("目录节点状态：[" + zooKeeper.exists("/liuzk", true) + "]");
            //创建另外一个子目录节点
            zooKeeper.create("/liuzk/childliuzktwo", "childliuzktwo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            System.out.println(new String(zooKeeper.getData("/liuzk/childliuzktwo", true, null)));
            //删除子目录节点
            zooKeeper.delete("/liuzk/childliuzkone", -1);
            zooKeeper.delete("/liuzk/childliuzktwo", -1);

            //删除父目录节点
            zooKeeper.delete("/liuzk", -1);
            ;System.out.println("执行删除后，目录节点状态：[" + zooKeeper.exists("/liuzk", true) + "]");

            zooKeeper.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
