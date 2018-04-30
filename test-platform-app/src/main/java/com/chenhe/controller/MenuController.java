package com.chenhe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MenuController {
    Logger logger = LoggerFactory.getLogger(MenuController.class);

    @RequestMapping("menu")
    public String menu(String uid, HttpServletRequest request, HttpServletResponse response) {
        //logger.info(request.getSession().getId());
        return "index";
    }
}
