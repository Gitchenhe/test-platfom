package com.chenhe.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenhe
 * @Date 2018-04-18 10:10
 * @desc
 **/
//@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    @ResponseBody
    public ModelAndView handler(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
