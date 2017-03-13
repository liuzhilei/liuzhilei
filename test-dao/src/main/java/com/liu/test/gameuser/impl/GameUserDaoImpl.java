package com.liu.test.gameuser.impl;

import com.liu.common.GameUser;
import com.liu.test.base.GenericBaseDaoImpl;
import com.liu.test.gameuser.GameUserDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liuzhilei on 2016/10/17.
 */
@Repository("gameUserDao")
public class GameUserDaoImpl extends GenericBaseDaoImpl implements GameUserDao {

    @Override
    public List<GameUser> queryListUsers(GameUser gameUser) {
        return this.executeForObjectList("GameUser.queryListUsers", gameUser);
    }

    @Override
    public int addUsers(GameUser gameUser) {
        return this.execute("GameUser.insertUser", gameUser);
    }

    @Override
    public int update(GameUser gameUser) {
        return this.execute("GameUser.updateUser", gameUser);
    }
}
