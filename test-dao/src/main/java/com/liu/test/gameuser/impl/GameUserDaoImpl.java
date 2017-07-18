
package com.liu.test.gameuser.impl;

import com.liu.common.GameUser;
import com.liu.test.base.GenericBaseDaoImpl;
import com.liu.test.gameuser.GameUserDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuzhilei on 2016/10/17.
 */
@Repository("gameUserDao")
public class GameUserDaoImpl extends GenericBaseDaoImpl implements GameUserDao {

    @Resource(name = "sqlSession")
    private SqlSession mySqlSession;

    @Override
    public List<GameUser> queryListUsers(GameUser gameUser) {
        return this.executeForObjectList("GameUser.queryListUsers", gameUser);
    }

    @Override
    public int addUsers(GameUser gameUser) {
        return mySqlSession.insert("GameUser.insertUser", gameUser);
    }

    @Override
    public int update(GameUser gameUser) {
        return this.execute("GameUser.updateUser", gameUser);
    }

    public SqlSession getMySqlSession() {
        return mySqlSession;
    }

    public void setMySqlSession(SqlSession mySqlSession) {
        this.mySqlSession = mySqlSession;
    }
}
