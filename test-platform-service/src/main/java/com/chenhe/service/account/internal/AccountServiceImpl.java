package com.chenhe.service.account.internal;

import com.chenhe.service.account.AccountService;
import com.chenhe.service.account.param.UserAuthParam;
import com.chenhe.service.dto.UserAccountEntity;
import com.chenhe.service.mapper.UserAccountMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    UserAccountMapper userAccountMapper;

    public UserAccountEntity queryUser(UserAuthParam param) {
        if(StringUtils.isBlank(param.getAccount()) || StringUtils.isBlank(param.getPassword())){
            throw new RuntimeException("用户名或密码为空");
        }
        return userAccountMapper.selectUser(param.getAccount(),param.getPassword());
    }

}
