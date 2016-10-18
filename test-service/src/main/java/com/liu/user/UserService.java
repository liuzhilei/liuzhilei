package com.liu.user;


import com.liu.common.GameUser;

import java.util.List;

/**
 * Created by liuzhilei on 2016/10/17.
 */
public interface UserService {

    List<GameUser> queryListUsers(GameUser gameUser);
}
