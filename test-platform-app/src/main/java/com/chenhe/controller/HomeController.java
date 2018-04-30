package com.chenhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @Date 2018-04-30 16:10
 * @desc
 **/
@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping("index")
    public String home() {
        return "home/index";
    }
}
