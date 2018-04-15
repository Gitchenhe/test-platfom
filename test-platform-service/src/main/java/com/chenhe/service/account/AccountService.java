package com.chenhe.service.account;

import com.chenhe.service.account.param.UserAuthParam;
import com.chenhe.service.dto.UserAccountEntity;

public interface AccountService {
    UserAccountEntity queryUser(UserAuthParam param);
}
