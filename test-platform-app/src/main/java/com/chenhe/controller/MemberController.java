package com.chenhe.controller;

import com.chenhe.base.TableAjaxResult;
import com.chenhe.service.dto.UserAccountEntity;
import com.chenhe.service.member.MemberService;
import com.chenhe.service.member.param.QueryUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018-04-29 21:51
 * @desc
 **/
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("list")
    public String mermberList(){
        return "member/member-list";
    }

    @RequestMapping("query")
    @ResponseBody
    public TableAjaxResult query(){
        TableAjaxResult result = new TableAjaxResult();

        QueryUserParam param = new QueryUserParam();
        List<UserAccountEntity> list =  memberService.queryMember(param);
        if (list == null){
            result.setCode(1);
            result.setMsg("用户列表为空");
        }else{
            result.setData(list);
        }

        return result;
    }
}
