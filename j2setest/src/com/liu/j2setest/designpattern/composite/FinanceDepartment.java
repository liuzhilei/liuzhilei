package com.liu.j2setest.designpattern.composite;

/**
 * Created by liuzhilei on 2017/3/2.
 */
public class FinanceDepartment extends Company {
    public FinanceDepartment() {

    }

    public FinanceDepartment(String name) {
        super(name);
    }

    @Override
    protected void add(Company company) {

    }

    @Override
    protected void display(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(new String(sb) + this.getName());
    }

    @Override
    protected void remove(Company company) {

    }
}
