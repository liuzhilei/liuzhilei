package com.liu.j2setest.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.util.List;
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
        /*
        分布式锁

        String s = redisDemo.acquireLock(1000000);
        if (s != null) {
            System.out.println("获得锁成功");
        } else {
            System.out.println("获得锁失败");
        }*/

        //redisDemo.jedisPool();

        //redisDemo.pipeTest();

        redisDemo.luaTest();
    }

    //测试jedis连接池
    private void jedisPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

        //初始化jedis连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.147.89", 6379);

        Jedis jedis = null;
        try {
            //从连接池中获取jedis对象
            jedis = jedisPool.getResource();

            jedis.set("hello", "world");
            System.out.println(jedis.get("hello"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                //这个close的实现，就是如果是jedis连接池，会归还连接。如果是直连方式，就关闭连接
                jedis.close();
            }
        }

    }

    //管道命令
    private void pipeTest() {
        //生成管道对象
        Pipeline pipelined = jedis.pipelined();

        //拼接命令，此时没有真正执行
        pipelined.set("lzl", "123");
        pipelined.del("hello");

        //执行命令
        //pipelined.sync();
        List<Object> objects = pipelined.syncAndReturnAll();
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    //测试lua
    private void luaTest() {
        //利用利用lua执行get命令
        String key = "lzl";
        String script = "return redis.call('get', KEYS[1])";
        Object result = jedis.eval(script, 1, key);
        System.out.println(result);

        //使用scriptLoad和evalsha，两个要结合使用
        //1.先利用scriptLoad把脚本加载到redis中
        String scriptSha = jedis.scriptLoad(script);

        //2.evalsha用来执行脚本的SHA1校验和
        Object o = jedis.evalsha(scriptSha, 1, key);
        System.out.println("evalsha :" + o);

    }

}
