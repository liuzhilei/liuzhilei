package com.liu.test.switchbutton.impl;

import com.liu.common.GameSwitch;
import com.liu.test.base.GenericBaseDaoImpl;
import com.liu.test.switchbutton.SwitchDao;
import org.springframework.stereotype.Repository;

/**
 * @author dushuangcheng
 * @create 2016-09-28 11:07
 */
@Repository("switchDao")
public class SwitchDaoImpl extends GenericBaseDaoImpl implements SwitchDao {
    /**
     * 插入开关
     *
     * @param info
     * @return
     */
    public int insertSwitch(GameSwitch info) {
        return this.execute("GameSwitch.insertSwitch", info);
    }
}
