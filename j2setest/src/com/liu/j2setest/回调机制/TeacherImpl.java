package com.liu.j2setest.回调机制;

/**
 * Created by liuzhilei on 2017/8/4.
 */
public class TeacherImpl implements Teacher {

    public void askQuestion(Student student) {
        System.out.println("老师问学生问题，三个手指代表几？");
        student.answer(this);
    }

    @Override
    public void receiveAnswer(int answer) {
        System.out.println("学生回答的答案是：" + answer);
    }
}
