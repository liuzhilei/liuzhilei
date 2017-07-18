package com.liu.service.gameswitch.impl;

import com.liu.common.GameSwitch;
import com.liu.service.gameswitch.SwitchService;
import com.liu.test.switchbutton.SwitchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by liuzhilei on 2017/7/18.
 */
@Service("switchService")
public class SwitchServiceImpl implements SwitchService {

    @Autowired
    private SwitchDao switchDao;

    @Override
    public int addSwitch() {
        int i = 0;
        try {
            GameSwitch gameSwitch = new GameSwitch();
            gameSwitch.setSwitchType("2");
            gameSwitch.setName("lll");
            gameSwitch.setMemo("测试开关");
            gameSwitch.setId(Long.parseLong(new Random().nextInt(1000) + ""));
            gameSwitch.setModifyUser("刘志磊");
            i = switchDao.insertSwitch(gameSwitch);
            i = i / 0;
        } catch (Exception e) {
            throw new RuntimeException("aspectJ抛出异常");
        }
        return i;
    }
}
