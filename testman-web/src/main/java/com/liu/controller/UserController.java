package com.liu.controller;

import com.liu.common.GameUser;
import com.liu.service.gameswitch.SwitchService;
import com.liu.service.user.UserService;
import com.liu.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhilei on 2016/10/17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController implements InitializingBean {

    private static int anInt = 0;

    @Override
    public void afterPropertiesSet() throws Exception {
        anInt++;
        System.out.println(this.getClass().getClassLoader().getClass().toString() + "======" + this.getClass().toString() + "==controller==" + anInt);
    }

    @Autowired
    @Qualifier("userService")
    private UserService userService;
    @Autowired
    private SwitchService switchService;

    //@Autowired
    //private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping("/getUsers.do")
    @ResponseBody
    public String getUsers(GameUser gameUser, Map<String, Object> result) {
        /*ModelAndView result = new ModelAndView();
        List<GameUser> list = userService.queryListUsers(gameUser);
        result.addObject("list",list);
        result.setViewName("user");*/
        try {
            List<GameUser> list = userService.queryListUsers(gameUser);
            result.put("list", list);
            /*
            StringBuilder sb = new StringBuilder();
            sb.append("URL").append("--").append("Class").append("--").append("Function").append('\n');
            //获取controller中的所有方法
            Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
            for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
                RequestMappingInfo info = m.getKey();
                HandlerMethod method = m.getValue();
                sb.append(info.getPatternsCondition()).append("--");
                sb.append(method.getMethod().getDeclaringClass()).append("--");
                sb.append(method.getMethod().getName()).append('\n');
            }
            System.out.println(sb.toString());*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user";
    }

    @RequestMapping("/addUsers.do")
    public String insertUser() {
        try {
            userService.insertUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user";
    }

    @RequestMapping("/aspectJAdd.do")
    public String aspectJInsertUser() {
        try {
            switchService.addSwitch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user";
    }
}
