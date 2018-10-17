package com.liu.j2setest.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuzhilei3 on 2018/8/24.
 * 使用guava的retryer优雅的重试
 */
public class GuavaRetryer {

    //定义实现Callable接口的方法，以便guava retryer能够调用
    private static Callable<Boolean> retryTestCall = new Callable() {
        @Override
        public Boolean call() throws Exception {
            //todo 在这执行正常的逻辑操作
            //int i = 1 / 0;
            return true;
        }
    };


    public static void main(String[] args) {
        //定义retry对象并设置相关策略
        Retryer<Boolean> retryer = RetryerBuilder
                .<Boolean>newBuilder()
                //抛异常重试，error不会重试
                .retryIfException()
                //返回false也会重试
                .retryIfResult(Predicates.equalTo(false))
                //重试策略
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                //尝试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                //追加监听器
                .withRetryListener(new MyRetryListener<Boolean>())
                .build();

        try {
            retryer.call(retryTestCall);
        } catch (Exception e) {
            System.out.println("抛出异常");
        }


    }

}
