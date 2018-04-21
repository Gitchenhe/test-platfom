package com.chenhe.controller;

import com.chenhe.service.transaction.TransactionParam;
import com.chenhe.service.transaction.TransactionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenhe
 * @Date 2018-04-18 10:05
 * @desc
 **/
@Controller
public class TransactionController {
    @Autowired
    TransactionTestService transactionTestService;


    @RequestMapping(value = "transaction/{transaction}/{exception}")
    @ResponseBody
    public String transactionTest(@PathVariable(value = "transaction" ,required = false) boolean transaction,@PathVariable(value = "exception",required = false) boolean exception){
        TransactionParam param = new TransactionParam();
        param.setExistTransaction(true);
        param.setThrowException(true);
        transactionTestService.testRequire(param);
        return "ok";
    }
}
