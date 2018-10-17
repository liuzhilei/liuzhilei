package com.liu.j2setest.guava;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;

/**
 * Created by liuzhilei3 on 2018/8/24.
 * 发生重试之后额外的操作，比如打印日志，监控等
 */
public class MyRetryListener<Boolean> implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {

        //注意：第一次调用也会调用
        System.out.println("当前为第几次重试：" + attempt.getAttemptNumber());

        //距离第一次重试的延迟
        System.out.println("delay = " + attempt.getDelaySinceFirstAttempt());

        //重试结果，是发生了异常，还是正常返回
        System.out.println("hasException = " + attempt.hasException());
        System.out.println("hasResult = " + attempt.hasResult());

        if (attempt.hasException()) {
            //错误原因
            System.out.println("causeBy:" + attempt.getExceptionCause().toString());
        } else {
            //正常返回结果
            System.out.println("result:" + attempt.getResult());
        }


    }
}
