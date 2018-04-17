package com.chenhe.controller;

import com.chenhe.base.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenhe
 * @Date 2018-04-13 15:29
 * @desc
 **/
@Controller
public class LoginController {
    @RequestMapping("login")
    public String loginPage(Model model) {
        model.addAttribute("msg", "请登录");
        return "login";
    }

    @PostMapping(value = "doLogin")
    @ResponseBody
    public AjaxResult doLogin(String account, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AjaxResult ajaxResult = new AjaxResult();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        try {
            subject.login(token);
            ajaxResult.setSuccess(true);
            ajaxResult.setData("/menu");
        } catch (AuthenticationException ex) {
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("身份认证失败!");
        }
        return ajaxResult;
    }
}
