package com.liu.j2setest.回调机制;

/**
 * Created by liuzhilei on 2017/8/4.
 * 回调接口
 */
public interface Teacher {

    void askQuestion(Student student);

    //该方法用户回调
    void receiveAnswer(int answer);
}
