package com.liu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuzhilei on 2016/10/17.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index.do")
    public String index(){
        return "index";
    }


}
