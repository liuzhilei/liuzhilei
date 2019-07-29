package com.liu.j2setest.effectivejava.hashcodeequalstest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Auther: liuzhilei
 * @Date: 2019/7/29
 * @Description:
 */
public class PhoneNumber {
    private int areaCode;

    private int prefix;

    private int lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return areaCode == that.areaCode &&
                prefix == that.prefix &&
                lineNumber == that.lineNumber;
    }

    @Override
    public int hashCode() {

        return Objects.hash(areaCode, prefix, lineNumber);
    }

    public static void main(String[] args) {
        Map<PhoneNumber, Integer> map = new HashMap<>();
        map.put(new PhoneNumber(1, 2, 3), 1);

        Integer integer = map.get(new PhoneNumber(1, 2, 3));
        System.out.println(integer);
    }
}
