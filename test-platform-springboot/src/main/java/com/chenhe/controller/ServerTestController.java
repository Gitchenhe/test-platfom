package com.chenhe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenhe
 * @Date 2018-09-11 17:35
 * @desc
 **/
@Controller
public class ServerTestController {
    Logger logger = LoggerFactory.getLogger(ServerTestController.class);
    @RequestMapping("test")
    @ResponseBody
    public String test(HttpServletRequest request){
        logger.info("[请求地址] : {},{},{}",request.getAttribute("X-real-ip"),request.getRemoteHost(),request.getRequestURL());

        return "OK";
    }
}
