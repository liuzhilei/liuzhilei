package com.liu.j2setest.一致性hash算法;

import java.util.Random;

/**
 * Created by liuzhilei3 on 2018/8/15.
 * 假设有1000w个数据项，100个存储节点，请设计一种算法合理地将他们存储在这些节点上。
 */
public class Demo1 {

    public static void main(String[] args) {
        Random random = new Random();

        //记录当node节点数改变，需要重新移动的数据项
        int changeSum = 0;

        for (int i = 0; i < 10000000; i++) {
            int j = random.nextInt(10000000);
            long hashKey = Math.abs(MurmurHash.hash(j + ""));

            //当node数量为100的时候，每个数据项会落到的某个节点上
            int nodeIndex = (int) (hashKey % 100);

            //新增一个节点，每个数据项会落到的某个节点上
            int newNodeIndex = (int) (hashKey % 101);

            //计算需要移动的数据项
            if(nodeIndex != newNodeIndex){
                changeSum ++;
            }

        }

        System.out.println("需要重新移动的数据项个数为：" + changeSum);
        /**
         * 需要重新移动的数据项个数为：9901527
         * 当有100个节点的时候，新增一个node节点，之前99%的数据都需要重新移动
         */

        /**
         * 一致性hash算法
         * 新增许多虚拟节点，落在虚拟节点上面的数据项会被相邻最近的真实节点处理。
         * 优点是在缩容和扩容的时候，只影响该节点附近的数据项的移动，将代价降低到最小
         */
    }
}
