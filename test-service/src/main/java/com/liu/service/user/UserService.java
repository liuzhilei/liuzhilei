package com.liu.service.user;


import com.liu.common.GameUser;

import java.util.List;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public interface UserService {

    List<GameUser> queryListUsers(GameUser gameUser);

    int insertUser();

    void testA();

    void testB();
}
