package com.liu.user.impl;

import com.liu.common.GameUser;
import com.liu.test.gameuser.GameUserDao;
import com.liu.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuzhilei on 2016/10/17.
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("gameUserDao")
    private GameUserDao gameUserDao;

    @Override
    public List<GameUser> queryListUsers(GameUser gameUser) {
        return gameUserDao.queryListUsers(gameUser);
    }
}
