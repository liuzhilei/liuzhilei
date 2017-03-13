package com.liu.test.switchbutton;

import com.liu.common.GameSwitch;

import java.util.List;

/**
 * Created by dushuangcheng on 2016/9/28.
 */

public interface SwitchDao {
    /**
     * 插入开关
     * @param info
     * @return
     */
    int insertSwitch(GameSwitch info);
}
