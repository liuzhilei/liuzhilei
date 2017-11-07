package com.liu.service.user.impl;

import com.liu.common.GameSwitch;
import com.liu.common.GameUser;
import com.liu.service.user.UserService;
import com.liu.test.gameuser.GameUserDao;
import com.liu.test.switchbutton.SwitchDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRED)
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void testA(){
        this.testB();//todo 注意：执行该方法，testb的事务不会生效，需要拿到该类的代理类才能生效
        /**
         * xml文件中添加 <aop:aspectj-autoproxy />
         * 然后获取到该类的代理类，就可以使用下面的事务
         */
        ((UserService)AopContext.currentProxy()).testB();// 这样对于B的事务注解，就可以正常执行
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void testB(){

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        anInt++;
        System.out.println(this.getClass().getClassLoader() + "======" + this.getClass().toString() + " ==service== " + anInt);
    }
}
