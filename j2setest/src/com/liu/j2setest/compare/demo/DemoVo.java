package com.liu.j2setest.compare.demo;

/**
 * Created by liuzhilei on 2017/9/3.
 * list中放入demo实体，两个list顺序不同，no字段不能比较，如何比较
 */
public class DemoVo implements Comparable<DemoVo> {

    @Override
    public int compareTo(DemoVo o) {
        if (this.name.compareTo(o.getName()) < 0 || this.age.compareTo(o.getAge()) < 0
                || this.kdanhao.compareTo(o.kdanhao) < 0 || this.wareId.compareTo(o.wareId) < 0) {
            return -1;
        } else if (this.name.compareTo(o.getName()) > 0 || this.age.compareTo(o.getAge()) > 0
                || this.kdanhao.compareTo(o.kdanhao) > 0 || this.wareId.compareTo(o.wareId) > 0) {
            return 1;
        }
        return 0;

    }

    private boolean com(DemoVo o) {
        if (this.name.equals(o.getName()) && this.age == o.getAge() && this.kdanhao.equals(o.kdanhao) && this.wareId.equals(o.wareId) && this.idCompanyFrom.equals(o.idCompanyFrom)
                && this.idCompanyTo.equals(o.idCompanyTo) && this.idStoreFrom.equals(o.idStoreFrom) && this.idStoreTo.equals(o.idStoreTo)) {
            return true;
        }
        return false;
    }

    private String name;
    private Integer age;
    private int no;//不比较这个字段
    private Long kdanhao = 1L;
    private Long wareId = 1L;
    private Integer idCompanyFrom = 1;
    private Integer idCompanyTo = 1;
    private Integer idStoreFrom = 1;
    private Integer idStoreTo = 1;

    public DemoVo() {
    }

    public DemoVo(String name, int age, int no) {
        this.name = name;
        this.age = age;
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DemoVo demoVo = (DemoVo) o;

        if (age != demoVo.age) return false;
        if (idCompanyFrom != null ? !idCompanyFrom.equals(demoVo.idCompanyFrom) : demoVo.idCompanyFrom != null)
            return false;
        if (idCompanyTo != null ? !idCompanyTo.equals(demoVo.idCompanyTo) : demoVo.idCompanyTo != null) return false;
        if (idStoreFrom != null ? !idStoreFrom.equals(demoVo.idStoreFrom) : demoVo.idStoreFrom != null) return false;
        if (idStoreTo != null ? !idStoreTo.equals(demoVo.idStoreTo) : demoVo.idStoreTo != null) return false;
        if (kdanhao != null ? !kdanhao.equals(demoVo.kdanhao) : demoVo.kdanhao != null) return false;
        if (name != null ? !name.equals(demoVo.name) : demoVo.name != null) return false;
        if (wareId != null ? !wareId.equals(demoVo.wareId) : demoVo.wareId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (kdanhao != null ? kdanhao.hashCode() : 0);
        result = 31 * result + (wareId != null ? wareId.hashCode() : 0);
        result = 31 * result + (idCompanyFrom != null ? idCompanyFrom.hashCode() : 0);
        result = 31 * result + (idCompanyTo != null ? idCompanyTo.hashCode() : 0);
        result = 31 * result + (idStoreFrom != null ? idStoreFrom.hashCode() : 0);
        result = 31 * result + (idStoreTo != null ? idStoreTo.hashCode() : 0);
        return result;
    }
}
