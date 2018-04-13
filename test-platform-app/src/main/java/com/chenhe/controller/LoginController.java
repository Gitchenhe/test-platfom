package com.chenhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenhe
 * @Date 2018-04-13 15:29
 * @desc
 **/
@Controller
public class LoginController {
    @RequestMapping("login")
    public String loginPage(Model model) {
        model.addAttribute("msg","请登录");
        return "login";
    }
}
