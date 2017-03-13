package com.liu.test.gameuser;


import com.liu.common.GameUser;

import java.util.List;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public interface GameUserDao {
    List<GameUser> queryListUsers(GameUser gameUser);

    int addUsers(GameUser gameUser);

    int update(GameUser gameUser);

}
