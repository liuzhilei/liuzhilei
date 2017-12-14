package com.liu.springtest.springPropertyEditor;

import java.util.Date;

/**
 * Created by liuzhilei on 2017/12/14.
 * spring中属性为日期，spring属性注入的时候会报错
 * spring提供两种方式解决：
 * 1.使用自定义属性编辑器，继承 PropertyEditorSupport 重写setAsText方法
 * 2.通过注册spring自带的属性编辑器CustomDateEditor来解决
 */
public class UserManager {
    private Date dataValue;

    public Date getDataValue() {
        return dataValue;
    }

    public void setDataValue(Date dataValue) {
        this.dataValue = dataValue;
    }

    public String toString() {
        return "dataValue: " + dataValue;
    }
}
