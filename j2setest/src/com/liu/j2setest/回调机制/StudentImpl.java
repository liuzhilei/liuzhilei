package com.liu.j2setest.回调机制;

/**
 * Created by liuzhilei on 2017/8/4.
 */
public class StudentImpl implements Student {

    @Override
    public void answer(Teacher callback) {
        System.out.println("学生回答了问题告诉了老师...");
        callback.receiveAnswer(3);
    }
}
