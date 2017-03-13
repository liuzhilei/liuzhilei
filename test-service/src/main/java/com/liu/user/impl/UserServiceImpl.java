package com.liu.user.impl;

import com.liu.common.GameSwitch;
import com.liu.common.GameUser;
import com.liu.test.gameuser.GameUserDao;
import com.liu.test.switchbutton.SwitchDao;
import com.liu.user.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("gameUserDao")
    private GameUserDao gameUserDao;
    @Autowired
    private SwitchDao switchDao;

    @Override
    @Transactional
    public List<GameUser> queryListUsers(GameUser gameUser) {
        List<GameUser> gameUsers = gameUserDao.queryListUsers(gameUser);
        try {
            gameUser = new GameUser();
            gameUser.setId(Long.parseLong(new Random().nextInt(1000) + ""));
            gameUser.setUserPin(new Random().nextInt() + "");
            gameUser.setMobilePhone("123456");
            if (gameUserDao.addUsers(gameUser) > 0) {
                throw new RuntimeException();
            }

            GameSwitch gameSwitch = new GameSwitch();
            //gameSwitch.setId(1l);
            gameSwitch.setMemo("");
            gameSwitch.setName("123");
            gameSwitch.setSwitchType("1");
            if (switchDao.insertSwitch(gameSwitch) > 0) {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gameUsers;
    }

    @Override
    public int addUser(GameUser gameUser) {
        int i;
        try {
            gameUser = new GameUser();
            gameUser.setId(Long.parseLong(new Random().nextInt(1000) + ""));
            gameUser.setUserPin(new Random().nextInt() + "");
            gameUser.setMobilePhone("123456");
            i = gameUserDao.addUsers(gameUser);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return i;
    }
}
