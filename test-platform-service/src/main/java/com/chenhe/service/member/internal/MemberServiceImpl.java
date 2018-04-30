package com.chenhe.service.member.internal;

import com.chenhe.service.dto.UserAccountEntity;
import com.chenhe.service.mapper.UserAccountMapper;
import com.chenhe.service.member.MemberService;
import com.chenhe.service.member.param.QueryUserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @Date 2018-04-30 14:02
 * @desc 用户列表操作
 **/
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    UserAccountMapper userAccountMapper;

    @Override
    public List<UserAccountEntity> queryMember(QueryUserParam param) {
        return userAccountMapper.queryUser(param);
    }
}
