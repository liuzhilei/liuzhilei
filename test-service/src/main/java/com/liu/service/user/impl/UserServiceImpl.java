package com.liu.service.user.impl;

import com.liu.common.GameSwitch;
import com.liu.common.GameUser;
import com.liu.service.user.UserService;
import com.liu.test.gameuser.GameUserDao;
import com.liu.test.switchbutton.SwitchDao;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * Created by liuzhilei on 2016/10/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService, InitializingBean {

    private static int anInt = 0;

    @Autowired
    @Qualifier("gameUserDao")
    private GameUserDao gameUserDao;
    @Autowired
    private SwitchDao switchDao;

    @Override
    //@Transactional
    public List<GameUser> queryListUsers(GameUser gameUser) {
        List<GameUser> gameUsers = gameUserDao.queryListUsers(gameUser);
        try {
            gameUser = new GameUser();
            gameUser.setId(Long.parseLong(new Random().nextInt(1000) + ""));
            gameUser.setUserPin(new Random().nextInt() + "");
            gameUser.setMobilePhone("123456");
            if (gameUserDao.addUsers(gameUser) < 0) {
                throw new RuntimeException();
            }

            GameSwitch gameSwitch = new GameSwitch();
            gameSwitch.setMemo("");
            gameSwitch.setName("123");
            switchDao.insertSwitch(gameSwitch);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gameUsers;
    }

    @Override
    @Transactional
    public int insertUser() {
        System.out.println(this.getClass());
        int i;
        try {
            GameUser gameUser = new GameUser();
            gameUser.setId(Long.parseLong(new Random().nextInt(1000) + ""));
            gameUser.setUserPin(new Random().nextInt() + "");
            gameUser.setMobilePhone("123456");
            i = gameUserDao.addUsers(gameUser);

            i = i / 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        anInt++;
        System.out.println(this.getClass().getClassLoader() + "======" + this.getClass().toString() + " ==service== " + anInt);
    }
}
