package com.chenhe.service.mapper;

import com.chenhe.service.dto.UserAccountEntity;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapper {
    UserAccountEntity selectUser(@Param("account") String account, @Param("password") String password);
}
