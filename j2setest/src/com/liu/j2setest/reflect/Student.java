package com.liu.j2setest.reflect;

/**
 * Created by liuzhilei on 2017/5/25.
 */
public class Student extends Person<Student> {

    private int classNumber;

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }
}
