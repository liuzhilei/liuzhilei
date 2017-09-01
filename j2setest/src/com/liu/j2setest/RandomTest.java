package com.liu.j2setest;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by liuzhilei on 2017/9/1.
 * random是一种伪随机
 * 如果需要安全的随机数，可以使用SecureRandom，这是一种强随机
 * 因为random中含有cas操作，高并发的时候，性能会很低。高并发的时候可以使用ThreadLocalRandom
 */
public class RandomTest {
    public static void main(String[] args) {
        randomTest();
        System.out.println();
        randomTest();
        System.out.println();
        randomTest();
    }

    /**
     * 测试random是伪随机，如果入参固定，那么生成的随机数都是固定的
     * 对于无参的构造方法，random自动生成了一个随机数
     */
    public static void randomTest() {
        Random random = new Random(10);
        for (int i = 0; i < 5; i++) {
            System.out.print(random.nextInt() + " === ");
        }
    }

    public static void secureRandomTest() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextInt();
    }

}
