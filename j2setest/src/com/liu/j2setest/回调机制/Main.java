package com.liu.j2setest.回调机制;

/**
 * Created by liuzhilei on 2017/8/4.
 * 回调例子，场景是老师问学生问题，学生回答问题以后，老师接收到答案
 */
public class Main {
    public static void main(String[] args) {
        Teacher teacher = new TeacherImpl();
        Student student = new StudentImpl();

        teacher.askQuestion(student);
    }
}
