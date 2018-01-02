package com.liu.j2setest.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * Created by Administrator on 2017/12/31 0031.
 * redis分布式锁
 */
public class RedisDemo {

    private Jedis jedis = new Jedis("192.168.147.89", 6379);
    private String lockName = "lock_name";

    /**
     * 获取锁,设置为自旋锁。如果没有获得锁，会等待一段时间然后继续获取锁
     *
     * @param timeout 超时时间
     * @return
     */
    public String acquireLock(int timeout) throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        long endTime = System.currentTimeMillis() + timeout;

        //如果未超时，就循环尝试获得锁
        while (System.currentTimeMillis() < endTime) {
            String result = jedis.set(lockName, uuid, "nx", "ex", 2000);
            if (result != null && "OK".equals(result.toUpperCase())) {
                return uuid;
            }
            //没有获得锁，就睡眠1秒，然后继续尝试获取
            System.out.println("sleep... ...");
            Thread.sleep(1000);
        }
        return null;
    }

    //释放锁
    public boolean releaseLock(String uuid) {
        while (true) {
            //利用watch监控key,如果key发生变化，之后的事务就不会执行。这是watch的作用
            jedis.watch(lockName);
            //key相等，即可释放锁
            if (uuid.equals(jedis.get(lockName))) {
                //事务的方式进行删除
                Transaction multi = jedis.multi();
                multi.del(lockName);
                if (multi.exec() == null) {
                    continue;
                }
            }
            //取消监控
            jedis.unwatch();
            break;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        RedisDemo redisDemo = new RedisDemo();
        String s = redisDemo.acquireLock(1000000);
        if (s != null) {
            System.out.println("获得锁成功");
        } else {
            System.out.println("获得锁失败");
        }
    }

}
